package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.query.CampanhaRepositoryQuery;

public interface CampanhaRepository extends JpaRepository<Campanha, Long>, CampanhaRepositoryQuery {

}
