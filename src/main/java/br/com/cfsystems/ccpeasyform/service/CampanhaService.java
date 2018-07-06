package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.CampanhaRepository;

@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	public Campanha atualizar(Long id, Campanha campanha) {
		Campanha campanhaSalva = buscarCampanhaPeloCodigo(id);
		
		BeanUtils.copyProperties(campanha, campanhaSalva, "id");
		return campanhaRepository.save(campanhaSalva);
	}
	
	public Campanha buscarCampanhaPeloCodigo(Long id) {
		Optional<Campanha> campanhaSalva = campanhaRepository.findById(id);
		if (!campanhaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return campanhaSalva.get();
	}

	public Campanha salvar(Campanha campanha) {
		return campanhaRepository.save(campanha);
	}	
	
}
