package br.com.cfsystems.ccpeasyform.model;

public enum TipoPergunta {

	MultiplaEscolha("Multipla Escolha"), RespostaUnica("Resposta Única"), Texto("Texto");

	private String tipo;

	private TipoPergunta(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
}
