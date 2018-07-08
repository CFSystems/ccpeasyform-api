package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.repository.query.PerguntaRepositoryQuery;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>, PerguntaRepositoryQuery {

}
