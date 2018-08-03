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
import br.com.cfsystems.ccpeasyform.model.Usuario;
import br.com.cfsystems.ccpeasyform.repository.UsuarioRepository;
import br.com.cfsystems.ccpeasyform.repository.filter.UsuarioFilter;
import br.com.cfsystems.ccpeasyform.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar(UsuarioFilter usuarioFilter, Pageable pageable){
		return usuarioRepository.filtrar(usuarioFilter, pageable);
	}
	
	@GetMapping("/listar")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		usuarioService.atualizarPropriedadeAtivo(id, ativo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}
	
}
