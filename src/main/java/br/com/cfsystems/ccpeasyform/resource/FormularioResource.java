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
import br.com.cfsystems.ccpeasyform.model.Formulario;
import br.com.cfsystems.ccpeasyform.repository.FormularioRepository;
import br.com.cfsystems.ccpeasyform.repository.filter.FormularioFilter;
import br.com.cfsystems.ccpeasyform.service.FormularioService;

@RestController
@RequestMapping("/formulario")
public class FormularioResource {
	
	@Autowired
	private FormularioRepository formularioRepository;
	
	@Autowired
	private FormularioService formularioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public Page<Formulario> formulario(FormularioFilter formularioFilter, Pageable pageable){
		return formularioRepository.filtrar(formularioFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Formulario> listar() {
		return formularioRepository.findAll();
	}
	
	@GetMapping("/listarAtivos")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Formulario> listarAtivos() {
		return formularioRepository.listarAtivos();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Formulario> buscarPeloId(@PathVariable Long id) {
		Optional<Formulario> formulario = formularioRepository.findById(id);
		return formulario.isPresent() ? ResponseEntity.ok(formulario.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Formulario> criar(@Valid @RequestBody Formulario formulario, HttpServletResponse response) {
		Formulario formularioSalva = formularioService.salvar(formulario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, formularioSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(formularioSalva);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Formulario> atualizar(@PathVariable Long id, @Valid @RequestBody Formulario formulario) {
		Formulario formularioSalva = formularioService.atualizar(id, formulario);
		return ResponseEntity.ok(formularioSalva);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		formularioService.atualizarPropriedadeAtivo(id, ativo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		formularioRepository.deleteById(id);
	}
}
