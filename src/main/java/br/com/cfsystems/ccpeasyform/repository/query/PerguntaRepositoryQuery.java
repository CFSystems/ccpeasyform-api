package br.com.cfsystems.ccpeasyform.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.repository.filter.PerguntaFilter;

public interface PerguntaRepositoryQuery {
	
	public Page<Pergunta> filtrar(PerguntaFilter perguntaFilter, Pageable pageable);

}
