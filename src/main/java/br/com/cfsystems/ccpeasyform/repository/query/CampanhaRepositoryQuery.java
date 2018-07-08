package br.com.cfsystems.ccpeasyform.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.filter.CampanhaFilter;

public interface CampanhaRepositoryQuery {
	
	public Page<Campanha> filtrar(CampanhaFilter campanhaFilter, Pageable pageable);

}
