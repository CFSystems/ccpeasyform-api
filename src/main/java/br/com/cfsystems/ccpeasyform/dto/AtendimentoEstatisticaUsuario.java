package br.com.cfsystems.ccpeasyform.dto;

import br.com.cfsystems.ccpeasyform.model.Usuario;

public class AtendimentoEstatisticaUsuario {

	private Usuario usuario;
	
	private Long total;

	public AtendimentoEstatisticaUsuario(Usuario usuario, Long total) {
		this.usuario = usuario;
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
