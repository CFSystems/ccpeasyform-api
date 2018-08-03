package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.FormularioPerguntaRepository;
import br.com.cfsystems.ccpeasyform.repository.FormularioRepository;

@Service
public class FormularioPerguntaService {
	
	@Autowired
	private FormularioPerguntaRepository formularioPerguntaRepository;
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	public void deletarFormularioResposta(Long idFormulario) {
		Formulario formulario = buscarFormularioPeloCodigo(idFormulario);
		this.formularioPerguntaRepository.excluirPorFormulario(formulario);
	}
	
	public Formulario buscarFormularioPeloCodigo(Long id) {
		Optional<Formulario> formularioSalva = formularioRepository.findById(id);
		if (!formularioSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return formularioSalva.get();
	}

}
