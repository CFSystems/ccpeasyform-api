package br.com.cfsystems.ccpeasyform.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FormularioPerguntaPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_formulario")
	private Long idFormulario;
	
	@Column(name = "id_pergunta")
	private Long idPergunta;

	public Long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(Long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Long getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(Long idPergunta) {
		this.idPergunta = idPergunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFormulario == null) ? 0 : idFormulario.hashCode());
		result = prime * result + ((idPergunta == null) ? 0 : idPergunta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormularioPerguntaPK other = (FormularioPerguntaPK) obj;
		if (idFormulario == null) {
			if (other.idFormulario != null)
				return false;
		} else if (!idFormulario.equals(other.idFormulario))
			return false;
		if (idPergunta == null) {
			if (other.idPergunta != null)
				return false;
		} else if (!idPergunta.equals(other.idPergunta))
			return false;
		return true;
	}
	
}
