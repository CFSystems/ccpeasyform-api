package br.com.cfsystems.ccpeasyform.repository;

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

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.filter.FormularioFilter;

public class FormularioRepositoryImpl implements FormularioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Formulario> filtrar(FormularioFilter formularioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Formulario> criteriaQuery = builder.createQuery(Formulario.class);
		
		Root<Formulario> root = criteriaQuery.from(Formulario.class);

		Predicate[] predicates = criarRestricoes(formularioFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Formulario> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(formularioFilter));
	}

	private Predicate[] criarRestricoes(FormularioFilter formularioFilter, CriteriaBuilder builder,
			Root<Formulario> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(formularioFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + formularioFilter.getNome().toLowerCase() + "%"));
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

	private Long total(FormularioFilter formularioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Formulario> root = criteria.from(Formulario.class);

		Predicate[] predicates = criarRestricoes(formularioFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
