package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.FormularioRepository;

@Service
public class FormularioService {
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	public Formulario atualizar(Long id, Formulario formulario) {
		Formulario formularioSalva = buscarFormularioPeloCodigo(id);
		
		BeanUtils.copyProperties(formulario, formularioSalva, "id");
		return formularioRepository.save(formularioSalva);
	}
	
	public Formulario buscarFormularioPeloCodigo(Long id) {
		Optional<Formulario> formularioSalva = formularioRepository.findById(id);
		if (!formularioSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return formularioSalva.get();
	}

	public Formulario salvar(Formulario formulario) {
		return formularioRepository.save(formulario);
	}	
	
	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Formulario formularioSalvo = buscarFormularioPeloCodigo(id);
		formularioSalvo.setAtivo(ativo);
		formularioRepository.save(formularioSalvo);
	}
	
}
