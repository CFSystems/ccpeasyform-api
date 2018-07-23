package br.com.cfsystems.ccpeasyform.dto;

import br.com.cfsystems.ccpeasyform.model.Pergunta;

public class RespostaEstatisticaFormulario {

	private Pergunta pergunta;
	
	private String resposta;
	
	private Long total;

	public RespostaEstatisticaFormulario(Pergunta pergunta, String resposta, Long total) {
		this.pergunta = pergunta;
		this.resposta = resposta;
		this.total = total;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
}
