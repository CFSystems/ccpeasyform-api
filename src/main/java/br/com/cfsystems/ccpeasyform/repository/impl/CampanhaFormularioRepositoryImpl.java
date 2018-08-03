package br.com.cfsystems.ccpeasyform.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.model.CampanhaFormulario;
import br.com.cfsystems.ccpeasyform.repository.query.CampanhaFormularioRepositoryQuery;

public class CampanhaFormularioRepositoryImpl implements CampanhaFormularioRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public void excluirPorCampanha(Campanha campanha) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaDelete<CampanhaFormulario> delete = builder.createCriteriaDelete(CampanhaFormulario.class);
		
		Root<CampanhaFormulario> root = delete.from(CampanhaFormulario.class);
		
		delete.where(builder.equal(root.get("campanha"), campanha));
		
		manager.createQuery(delete).executeUpdate();
		
	}
	
}
