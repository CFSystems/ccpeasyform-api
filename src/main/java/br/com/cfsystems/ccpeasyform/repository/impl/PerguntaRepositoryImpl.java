package br.com.cfsystems.ccpeasyform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.repository.filter.PerguntaFilter;
import br.com.cfsystems.ccpeasyform.repository.query.PerguntaRepositoryQuery;

public class PerguntaRepositoryImpl implements PerguntaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Pergunta> filtrar(PerguntaFilter perguntaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pergunta> criteriaQuery = builder.createQuery(Pergunta.class);
		
		Root<Pergunta> root = criteriaQuery.from(Pergunta.class);

		Predicate[] predicates = criarRestricoes(perguntaFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Pergunta> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(perguntaFilter));
	}

	private Predicate[] criarRestricoes(PerguntaFilter perguntaFilter, CriteriaBuilder builder,
			Root<Pergunta> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(perguntaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + perguntaFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(PerguntaFilter perguntaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pergunta> root = criteria.from(Pergunta.class);

		Predicate[] predicates = criarRestricoes(perguntaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
