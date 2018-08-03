package br.com.cfsystems.ccpeasyform.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.model.FormularioPergunta;
import br.com.cfsystems.ccpeasyform.repository.query.FormularioPerguntaRepositoryQuery;

public class FormularioPerguntaRepositoryImpl implements FormularioPerguntaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public void excluirPorFormulario(Formulario formulario) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaDelete<FormularioPergunta> delete = builder.createCriteriaDelete(FormularioPergunta.class);
		
		Root<FormularioPergunta> root = delete.from(FormularioPergunta.class);
		
		delete.where(builder.equal(root.get("formulario"), formulario));
		
		manager.createQuery(delete).executeUpdate();
		
	}
	
}
