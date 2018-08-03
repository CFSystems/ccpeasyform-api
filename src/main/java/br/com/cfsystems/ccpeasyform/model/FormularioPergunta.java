package br.com.cfsystems.ccpeasyform.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "formulario_pergunta")
public class FormularioPergunta {

	@EmbeddedId
	private FormularioPerguntaPK id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_formulario", insertable = false, updatable = false)
	private Formulario formulario;
	
	@ManyToOne
	@JoinColumn(name = "id_pergunta", insertable = false, updatable = false)
	private Pergunta pergunta;

	private Long ordem;

	public FormularioPerguntaPK getId() {
		return id;
	}

	public void setId(FormularioPerguntaPK id) {
		this.id = id;
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

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
}
