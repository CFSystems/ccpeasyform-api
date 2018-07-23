package br.com.cfsystems.ccpeasyform.repository.query;

import java.time.LocalDate;
import java.util.List;

import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaCampanha;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaCompleto;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaDia;
import br.com.cfsystems.ccpeasyform.dto.AtendimentoEstatisticaUsuario;

public interface AtendimentoRepositoryQuery {
	
	public List<AtendimentoEstatisticaCampanha> porCampanha();
	
	public List<AtendimentoEstatisticaUsuario> porUsuario();
	
	public List<AtendimentoEstatisticaDia> porDia(LocalDate mesReferencia);
	
	public List<AtendimentoEstatisticaCompleto> completo();

}
