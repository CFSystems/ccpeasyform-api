package br.com.cfsystems.ccpeasyform.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Atendimento;
import br.com.cfsystems.ccpeasyform.repository.AtendimentoRepository;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	public Atendimento salvar(Atendimento atendimento) {
		atendimento.setDataAtendimento(LocalDate.now());;
		return atendimentoRepository.save(atendimento);
	}
	
}
