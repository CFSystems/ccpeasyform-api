package br.com.cfsystems.ccpeasyform.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaCampanha;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaCompleto;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaDia;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaUsuario;
import br.com.cfsystems.ccpeasyform.model.Atendimento;
import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.model.Contato;
import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.model.Resposta;
import br.com.cfsystems.ccpeasyform.model.Usuario;
import br.com.cfsystems.ccpeasyform.repository.query.AtendimentoRepositoryQuery;

public class AtendimentoRepositoryImpl implements AtendimentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<AtendimentoEstatisticaCampanha> porCampanha() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<AtendimentoEstatisticaCampanha> criteriaQuery = criteriaBuilder
				.createQuery(AtendimentoEstatisticaCampanha.class);

		Root<Atendimento> root = criteriaQuery.from(Atendimento.class);

		criteriaQuery.select(criteriaBuilder.construct(AtendimentoEstatisticaCampanha.class, root.get("campanha"),
				criteriaBuilder.count(root.get("id"))));

		criteriaQuery.groupBy(root.get("campanha"));

		TypedQuery<AtendimentoEstatisticaCampanha> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public List<AtendimentoEstatisticaUsuario> porUsuario() {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<AtendimentoEstatisticaUsuario> criteriaQuery = criteriaBuilder
				.createQuery(AtendimentoEstatisticaUsuario.class);

		Root<Atendimento> root = criteriaQuery.from(Atendimento.class);

		criteriaQuery.select(criteriaBuilder.construct(AtendimentoEstatisticaUsuario.class, root.get("usuario"),
				criteriaBuilder.count(root.get("id"))));

		criteriaQuery.groupBy(root.get("usuario"));

		TypedQuery<AtendimentoEstatisticaUsuario> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public List<AtendimentoEstatisticaDia> porDia(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<AtendimentoEstatisticaDia> criteriaQuery = criteriaBuilder
				.createQuery(AtendimentoEstatisticaDia.class);

		Root<Atendimento> root = criteriaQuery.from(Atendimento.class);

		criteriaQuery.select(criteriaBuilder.construct(AtendimentoEstatisticaDia.class, root.get("dataAtendimento"),
				criteriaBuilder.count(root.get("id"))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(root.get("dataAtendimento"), primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get("dataAtendimento"), ultimoDia));

		criteriaQuery.groupBy(root.get("dataAtendimento"));

		TypedQuery<AtendimentoEstatisticaDia> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public List<AtendimentoEstatisticaCompleto> completo() {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<AtendimentoEstatisticaCompleto> criteriaQuery = criteriaBuilder
				.createQuery(AtendimentoEstatisticaCompleto.class);

		Root<Atendimento> root = criteriaQuery.from(Atendimento.class);
		Join<Atendimento, Contato> contato = root.join("contato");
		Join<Atendimento, Campanha> campanha = root.join("campanha");
		Join<Atendimento, Formulario> formulario = root.join("formulario");
		Join<Atendimento, Usuario> usuario = root.join("usuario");
		Join<Atendimento, Resposta> resposta = root.join("respostas");
		Join<Resposta, Pergunta> pergunta = resposta.join("pergunta");

		criteriaQuery.select(criteriaBuilder.construct(AtendimentoEstatisticaCompleto.class, root.get("id"),
				contato.get("nome"), campanha.get("nome"), formulario.get("nome"), usuario.get("nome"), root.get("dataAtendimento"),
				pergunta.get("nome"), resposta.get("resposta")));

		TypedQuery<AtendimentoEstatisticaCompleto> typedQuery = manager.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}
}
