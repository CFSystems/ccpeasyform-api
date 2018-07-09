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

import br.com.cfsystems.ccpeasyform.model.Contato;
import br.com.cfsystems.ccpeasyform.repository.filter.ContatoFilter;
import br.com.cfsystems.ccpeasyform.repository.query.ContatoRepositoryQuery;

public class ContatoRepositoryImpl implements ContatoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Contato> filtrar(ContatoFilter contatoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contato> criteriaQuery = builder.createQuery(Contato.class);
		
		Root<Contato> root = criteriaQuery.from(Contato.class);

		Predicate[] predicates = criarRestricoes(contatoFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Contato> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(contatoFilter));
	}

	private Predicate[] criarRestricoes(ContatoFilter contatoFilter, CriteriaBuilder builder,
			Root<Contato> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(contatoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + contatoFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(contatoFilter.getCpf())) {
			predicates.add(builder.like(builder.lower(root.get("cpf")),
					"%" + contatoFilter.getCpf().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(contatoFilter.getIdentificador())) {
			predicates.add(builder.like(builder.lower(root.get("identificador")),
					"%" + contatoFilter.getIdentificador().toLowerCase() + "%"));
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

	private Long total(ContatoFilter contatoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Contato> root = criteria.from(Contato.class);

		Predicate[] predicates = criarRestricoes(contatoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
