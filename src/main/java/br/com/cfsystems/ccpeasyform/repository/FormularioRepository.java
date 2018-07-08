package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.query.FormularioRepositoryQuery;

public interface FormularioRepository extends JpaRepository<Formulario, Long>, FormularioRepositoryQuery {

}
