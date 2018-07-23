package br.com.cfsystems.ccpeasyform.dto;

import br.com.cfsystems.ccpeasyform.model.Campanha;

public class AtendimentoEstatisticaCampanha {
	
	private Campanha campanha;
	
	private Long total;

	public AtendimentoEstatisticaCampanha(Campanha campanha, Long total) {
		this.campanha = campanha;
		this.total = total;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
