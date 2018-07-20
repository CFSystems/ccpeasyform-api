package br.com.cfsystems.ccpeasyform.resource;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cfsystems.ccpeasyform.event.RecursoCriadoEvent;
import br.com.cfsystems.ccpeasyform.model.Contato;
import br.com.cfsystems.ccpeasyform.repository.ContatoRepository;
import br.com.cfsystems.ccpeasyform.repository.filter.ContatoFilter;
import br.com.cfsystems.ccpeasyform.service.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoResource {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public Page<Contato> contato(ContatoFilter contatoFilter, Pageable pageable ){
		return contatoRepository.filtrar(contatoFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Contato> criar(@Valid @RequestBody Contato contato, HttpServletResponse response) {
		Contato contatoSalvo = contatoService.salvar(contato);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, contatoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Contato> buscarPeloId(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		return contato.isPresent() ? ResponseEntity.ok(contato.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		contatoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato) {
		Contato contatoSalvo = contatoService.atualizar(id, contato);
		return ResponseEntity.ok(contatoSalvo);
	}
	
}
