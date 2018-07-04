package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Opcao;
import br.com.cfsystems.ccpeasyform.repository.OpcaoRepository;

@Service
public class OpcaoService {
	
	@Autowired
	private OpcaoRepository opcaoRepository;
	
	public Opcao atualizar(Long id, Opcao opcao) {
		Opcao opcaoSalva = buscarOpcaoPeloCodigo(id);
		
		BeanUtils.copyProperties(opcao, opcaoSalva, "id");
		return opcaoRepository.save(opcaoSalva);
	}
	
	public Opcao buscarOpcaoPeloCodigo(Long id) {
		Optional<Opcao> opcaoSalva = opcaoRepository.findById(id);
		if (!opcaoSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return opcaoSalva.get();
	}	
	
	
}
