package br.com.cfsystems.ccpeasyform.dto;

import java.time.LocalDate;

public class AtendimentoEstatisticaCompleto {

	private Long id;
	private String contato;
	private String campanha;
	private String cliente;
	private String formulario;
	private String usuario;
	private LocalDate dataAtendimento;
	private String pergunta;
	private String resposta;
	
	public AtendimentoEstatisticaCompleto(Long id, String contato, String campanha, String cliente, String formulario, String usuario,
			LocalDate dataAtendimento, String pergunta, String resposta) {
		this.id = id;
		this.contato = contato;
		this.campanha = campanha;
		this.cliente = cliente;
		this.formulario = formulario;
		this.usuario = usuario;
		this.dataAtendimento = dataAtendimento;
		this.pergunta = pergunta;
		this.resposta = resposta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public String getCliente() {
		return cliente;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(LocalDate dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
}
