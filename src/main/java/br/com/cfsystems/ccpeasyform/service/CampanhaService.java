package br.com.cfsystems.ccpeasyform.service;

import java.time.LocalDate;
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
		campanha.setDataInicio(null);
		campanha.setDataTermino(null);
		
		return campanhaRepository.save(campanha);
	}
	
	public void mudarStatus(Long id) {
		Campanha campanhaSalva = buscarCampanhaPeloCodigo(id);
		if(campanhaSalva.getStatus().equalsIgnoreCase("Pendente")) {
			campanhaSalva.setStatus("Em Andamento");
			campanhaSalva.setDataInicio(LocalDate.now());
			campanhaRepository.save(campanhaSalva);
		} else if (campanhaSalva.getStatus().equalsIgnoreCase("Em Andamento")) {
			campanhaSalva.setStatus("Concluída");
			campanhaSalva.setDataTermino(LocalDate.now());
			campanhaRepository.save(campanhaSalva);
		}
	}
	
}
