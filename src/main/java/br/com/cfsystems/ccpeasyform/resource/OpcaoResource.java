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
import br.com.cfsystems.ccpeasyform.model.Opcao;
import br.com.cfsystems.ccpeasyform.repository.OpcaoRepository;
import br.com.cfsystems.ccpeasyform.service.OpcaoService;

@RestController
@RequestMapping("/opcao")
public class OpcaoResource {

	@Autowired
	private OpcaoRepository opcaoRepository;
	
	@Autowired
	private OpcaoService opcaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Opcao> pesquisa(){
		return opcaoRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Opcao> criar(@Valid @RequestBody Opcao opcao, HttpServletResponse response) {
		Opcao opcaoSalva = opcaoRepository.save(opcao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, opcaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(opcaoSalva);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Opcao> buscarPeloId(@PathVariable Long id) {
		Optional<Opcao> opcao = opcaoRepository.findById(id);
		return opcao.isPresent() ? ResponseEntity.ok(opcao.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		opcaoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Opcao> atualizar(@PathVariable Long id, @Valid @RequestBody Opcao opcao) {
		Opcao opcaoSalva = opcaoService.atualizar(id, opcao);
		return ResponseEntity.ok(opcaoSalva);
	}
	
}
