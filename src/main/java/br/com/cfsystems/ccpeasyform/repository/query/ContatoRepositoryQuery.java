package br.com.cfsystems.ccpeasyform.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cfsystems.ccpeasyform.model.Contato;
import br.com.cfsystems.ccpeasyform.repository.filter.ContatoFilter;

public interface ContatoRepositoryQuery {
	
	public Page<Contato> filtrar(ContatoFilter contatoFilter, Pageable pageable);

}
