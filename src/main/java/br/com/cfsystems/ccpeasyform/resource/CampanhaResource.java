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
import br.com.cfsystems.ccpeasyform.model.Campanha;
import br.com.cfsystems.ccpeasyform.repository.CampanhaRepository;
import br.com.cfsystems.ccpeasyform.repository.filter.CampanhaFilter;
import br.com.cfsystems.ccpeasyform.service.CampanhaService;

@RestController
@RequestMapping("/campanha")
public class CampanhaResource {
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaService campanhaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public Page<Campanha> campanha(CampanhaFilter campanhaFilter, Pageable pageable){
		return campanhaRepository.filtrar(campanhaFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Campanha> criar(@Valid @RequestBody Campanha campanha, HttpServletResponse response) {
		Campanha campanhaSalva = campanhaService.salvar(campanha);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, campanhaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(campanhaSalva);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Campanha> buscarPeloId(@PathVariable Long id) {
		Optional<Campanha> campanha = campanhaRepository.findById(id);
		return campanha.isPresent() ? ResponseEntity.ok(campanha.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		campanhaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Campanha> atualizar(@PathVariable Long id, @Valid @RequestBody Campanha campanha) {
		Campanha campanhaSalva = campanhaService.atualizar(id, campanha);
		return ResponseEntity.ok(campanhaSalva);
	}
	
	@PutMapping("/{id}/mudarStatus")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void mudarStatus(@PathVariable Long id) {
		campanhaService.mudarStatus(id);
	}
	
}
