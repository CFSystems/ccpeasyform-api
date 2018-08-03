package br.com.cfsystems.ccpeasyform.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CampanhaFormularioPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_campanha")
	private Long idCampanha;

	@Column(name = "id_formulario")
	private Long idFormulario;

	public Long getIdFormulario() {
		return idFormulario;
	}

	public void setIdFormulario(Long idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCampanha == null) ? 0 : idCampanha.hashCode());
		result = prime * result + ((idFormulario == null) ? 0 : idFormulario.hashCode());
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
		CampanhaFormularioPK other = (CampanhaFormularioPK) obj;
		if (idCampanha == null) {
			if (other.idCampanha != null)
				return false;
		} else if (!idCampanha.equals(other.idCampanha))
			return false;
		if (idFormulario == null) {
			if (other.idFormulario != null)
				return false;
		} else if (!idFormulario.equals(other.idFormulario))
			return false;
		return true;
	}

}
