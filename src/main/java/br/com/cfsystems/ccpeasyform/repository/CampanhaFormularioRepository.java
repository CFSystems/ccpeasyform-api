package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.CampanhaFormulario;
import br.com.cfsystems.ccpeasyform.repository.query.CampanhaFormularioRepositoryQuery;

public interface CampanhaFormularioRepository extends JpaRepository<CampanhaFormulario, Long>, CampanhaFormularioRepositoryQuery {

}
