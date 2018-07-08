package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Contato;
import br.com.cfsystems.ccpeasyform.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoService;
	
	public Contato atualizar(Long id, Contato contato) {
		Contato contatoSalvo = buscarContatoPeloCodigo(id);
		
		BeanUtils.copyProperties(contato, contatoSalvo, "id");
		return contatoService.save(contatoSalvo);
	}
	
	public Contato buscarContatoPeloCodigo(Long id) {
		Optional<Contato> contatoSalvo = contatoService.findById(id);
		if (!contatoSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return contatoSalvo.get();
	}

	public Contato salvar(Contato contato) {
		return contatoService.save(contato);
	}	
	
}
