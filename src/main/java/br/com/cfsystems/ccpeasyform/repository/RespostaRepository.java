package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Resposta;
import br.com.cfsystems.ccpeasyform.repository.query.RespostaRepositoryQuery;

public interface RespostaRepository extends JpaRepository<Resposta, Long>, RespostaRepositoryQuery {

}
