package br.com.cfsystems.ccpeasyform.dto;

import java.time.LocalDate;

public class AtendimentoEstatisticaDia {

	private LocalDate dia;
	
	private Long total;

	public AtendimentoEstatisticaDia(LocalDate dia, Long total) {
		this.dia = dia;
		this.total = total;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
