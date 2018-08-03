package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.FormularioPergunta;
import br.com.cfsystems.ccpeasyform.repository.query.FormularioPerguntaRepositoryQuery;

public interface FormularioPerguntaRepository extends JpaRepository<FormularioPergunta, Long>, FormularioPerguntaRepositoryQuery {

}
