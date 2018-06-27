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

import br.com.cfsystems.ccpeasyform.model.Opcao;
import br.com.cfsystems.ccpeasyform.repository.filter.OpcaoFilter;

public class OpcaoRepositoryImpl implements OpcaoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Opcao> filtrar(OpcaoFilter opcaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Opcao> criteriaQuery = builder.createQuery(Opcao.class);
		
		Root<Opcao> root = criteriaQuery.from(Opcao.class);

		Predicate[] predicates = criarRestricoes(opcaoFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Opcao> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	private Predicate[] criarRestricoes(OpcaoFilter opcaoFilter, CriteriaBuilder builder,
			Root<Opcao> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (opcaoFilter.getId() != null) {
			predicates.add(builder.equal(root.join("pergunta").get("id"), opcaoFilter.getId()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
