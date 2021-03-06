package br.com.cfsystems.ccpeasyform.resource;

import java.util.List;
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
import br.com.cfsystems.ccpeasyform.model.Pergunta;
import br.com.cfsystems.ccpeasyform.repository.PerguntaRepository;
import br.com.cfsystems.ccpeasyform.repository.filter.PerguntaFilter;
import br.com.cfsystems.ccpeasyform.service.PerguntaService;

@RestController
@RequestMapping("/pergunta")
public class PerguntaResource {
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private PerguntaService perguntaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public Page<Pergunta> pesquisar(PerguntaFilter perguntaFilter, Pageable pageable){
		return perguntaRepository.filtrar(perguntaFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Pergunta> listar() {
		return perguntaRepository.findAll();
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Pergunta> buscarPeloId(@PathVariable Long id) {
		Optional<Pergunta> pergunta = perguntaRepository.findById(id);
		return pergunta.isPresent() ? ResponseEntity.ok(pergunta.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Pergunta> criar(@Valid @RequestBody Pergunta pergunta, HttpServletResponse response) {
		Pergunta perguntaSalva = perguntaService.salvar(pergunta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, perguntaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(perguntaSalva);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Pergunta> atualizar(@PathVariable Long id, @Valid @RequestBody Pergunta pergunta) {
		Pergunta perguntaSalva = perguntaService.atualizar(id, pergunta);
		return ResponseEntity.ok(perguntaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		perguntaRepository.deleteById(id);
	}
	
}
