package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.filter.FormularioFilter;

public interface FormularioRepositoryQuery {
	
	public Page<Formulario> filtrar(FormularioFilter formularioFilter, Pageable pageable);

}
