package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.exceptionhandler.PerguntaEmUsoException;
import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.repository.PerguntaRepository;

@Service
public class PerguntaService {
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	public Pergunta atualizar(Long id, Pergunta pergunta) {
		Pergunta perguntaSalva = buscarPerguntaPeloCodigo(id);
		
		if (perguntaSalva.getEmUso()) {
			throw new PerguntaEmUsoException();
		}
		
		BeanUtils.copyProperties(pergunta, perguntaSalva, "id", "emUso");
		return perguntaRepository.save(perguntaSalva);
	}

	public void atualizarPropriedadeEmUso(Long id) {
		Pergunta perguntaSalva = buscarPerguntaPeloCodigo(id);
		perguntaSalva.setEmUso(true);
		perguntaRepository.save(perguntaSalva);
	}
	
	public Pergunta buscarPerguntaPeloCodigo(Long id) {
		Optional<Pergunta> perguntaSalva = perguntaRepository.findById(id);
		if (!perguntaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return perguntaSalva.get();
	}

	public Pergunta salvar(Pergunta pergunta) {
		pergunta.setEmUso(false);
		return perguntaRepository.save(pergunta);
	}	
	
	
}
