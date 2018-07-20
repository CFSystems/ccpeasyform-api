package br.com.cfsystems.ccpeasyform.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cfsystems.ccpeasyform.event.RecursoCriadoEvent;
import br.com.cfsystems.ccpeasyform.model.Atendimento;
import br.com.cfsystems.ccpeasyform.repository.AtendimentoRepository;
import br.com.cfsystems.ccpeasyform.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoResource {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private AtendimentoService atendimentoService;
		
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Atendimento> atendimento(){
		return atendimentoRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Atendimento> criar(@Valid @RequestBody Atendimento atendimento, HttpServletResponse response) {
		Atendimento atendimentoSalvo = atendimentoService.salvar(atendimento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, atendimentoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(atendimentoSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Atendimento> buscarPeloId(@PathVariable Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
		return atendimento.isPresent() ? ResponseEntity.ok(atendimento.get()) : ResponseEntity.notFound().build();
	}
	
}
