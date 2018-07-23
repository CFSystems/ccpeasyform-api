package br.com.cfsystems.ccpeasyform.repository.query;

import java.util.List;

import br.com.cfsystems.ccpeasyform.dto.RespostaEstatisticaFormulario;

public interface RespostaRepositoryQuery {
	
	public List<RespostaEstatisticaFormulario> porFormulario(Long idCampanha, Long idFormulario, Long idPergunta);

}
