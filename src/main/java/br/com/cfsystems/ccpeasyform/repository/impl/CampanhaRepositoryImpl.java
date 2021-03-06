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

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.filter.CampanhaFilter;
import br.com.cfsystems.ccpeasyform.repository.query.CampanhaRepositoryQuery;

public class CampanhaRepositoryImpl implements CampanhaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Campanha> criteriaQuery = builder.createQuery(Campanha.class);
		
		Root<Campanha> root = criteriaQuery.from(Campanha.class);

		Predicate[] predicates = criarRestricoes(campanhaFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Campanha> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(campanhaFilter));
	}

	private Predicate[] criarRestricoes(CampanhaFilter campanhaFilter, CriteriaBuilder builder,
			Root<Campanha> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(campanhaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + campanhaFilter.getNome().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(campanhaFilter.getStatus())) {
			predicates.add(builder.like(builder.lower(root.get("status")),
					"%" + campanhaFilter.getStatus().toLowerCase() + "%"));
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

	private Long total(CampanhaFilter campanhaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Campanha> root = criteria.from(Campanha.class);

		Predicate[] predicates = criarRestricoes(campanhaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
