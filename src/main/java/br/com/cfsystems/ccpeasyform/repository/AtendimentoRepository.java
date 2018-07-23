package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Atendimento;
import br.com.cfsystems.ccpeasyform.repository.query.AtendimentoRepositoryQuery;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>, AtendimentoRepositoryQuery {

}
