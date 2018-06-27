package br.com.cfsystems.ccpeasyform.repository;

import java.util.List;

import br.com.cfsystems.ccpeasyform.model.Opcao;
import br.com.cfsystems.ccpeasyform.repository.filter.OpcaoFilter;

public interface OpcaoRepositoryQuery {

	public List<Opcao> filtrar(OpcaoFilter opcaoFilter);
	
}
