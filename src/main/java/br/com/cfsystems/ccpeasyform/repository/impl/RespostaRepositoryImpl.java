package br.com.cfsystems.ccpeasyform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.cfsystems.ccpeasyform.dto.RespostaEstatisticaFormulario;
import br.com.cfsystems.ccpeasyform.model.Atendimento;
import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.model.Resposta;
import br.com.cfsystems.ccpeasyform.repository.query.RespostaRepositoryQuery;

public class RespostaRepositoryImpl implements RespostaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<RespostaEstatisticaFormulario> porFormulario(Long idCampanha, Long idFormulario, Long idPergunta) {
		
		Campanha campanha = new Campanha();
		campanha.setId(idCampanha);
		
		Formulario formulario = new Formulario();
		formulario.setId(idFormulario);
		
		Pergunta pergunta = new Pergunta();
		pergunta.setId(idPergunta);
		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<RespostaEstatisticaFormulario> criteriaQuery = criteriaBuilder
				.createQuery(RespostaEstatisticaFormulario.class);

		Root<Atendimento> root = criteriaQuery.from(Atendimento.class);
		Join<Atendimento, Resposta> resposta = root.join("respostas");  
		
		criteriaQuery.select(criteriaBuilder.construct(RespostaEstatisticaFormulario.class,
				resposta.get("pergunta"), resposta.get("resposta"), criteriaBuilder.count(resposta.get("resposta"))));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(criteriaBuilder.equal(root.get("campanha"), campanha));
		predicates.add(criteriaBuilder.equal(root.get("formulario"), formulario));
		predicates.add(criteriaBuilder.equal(resposta.get("pergunta"), pergunta));
		
		Predicate[] preds = predicates.toArray(new Predicate[predicates.size()]); 
		
		criteriaQuery.where(preds);
		
		criteriaQuery.groupBy(resposta.get("pergunta"), resposta.get("resposta"));

		TypedQuery<RespostaEstatisticaFormulario> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

}
