package br.com.cfsystems.ccpeasyform.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "campanha_formulario")
public class CampanhaFormulario {

	@EmbeddedId
	private CampanhaFormularioPK id;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_campanha", insertable = false, updatable = false)
	private Campanha campanha;

	@ManyToOne
	@JoinColumn(name = "id_formulario", insertable = false, updatable = false)
	private Formulario formulario;

	private Long ordem;

	public CampanhaFormularioPK getId() {
		return id;
	}

	public void setId(CampanhaFormularioPK id) {
		this.id = id;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Long getOrdem() {
		return ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

}
