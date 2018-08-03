package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.CampanhaFormularioRepository;
import br.com.cfsystems.ccpeasyform.repository.CampanhaRepository;

@Service
public class CampanhaFormularioService {
	
	@Autowired
	private CampanhaFormularioRepository campanhaFormularioRepository;
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	public void deletarCampanhaFormulario(Long idCampanha) {
		Campanha campanha = buscarCampanhaPeloCodigo(idCampanha);
		this.campanhaFormularioRepository.excluirPorCampanha(campanha);
	}
	
	public Campanha buscarCampanhaPeloCodigo(Long id) {
		Optional<Campanha> campanhaSalva = campanhaRepository.findById(id);
		if (!campanhaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return campanhaSalva.get();
	}

}
