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
import br.com.cfsystems.ccpeasyform.model.Execucao;
import br.com.cfsystems.ccpeasyform.repository.ExecucaoRepository;

@RestController
@RequestMapping("/execucao")
public class ExecucaoResource {
	
	@Autowired
	private ExecucaoRepository execucaoRepository;
		
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXECUCAO') and #oauth2.hasScope('read')")
	public List<Execucao> execucao(){
		return execucaoRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EXECUCAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Execucao> criar(@Valid @RequestBody Execucao execucao, HttpServletResponse response) {
		Execucao execucaoSalva = execucaoRepository.save(execucao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, execucaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(execucaoSalva);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXECUCAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Execucao> buscarPeloId(@PathVariable Long id) {
		Optional<Execucao> execucao = execucaoRepository.findById(id);
		return execucao.isPresent() ? ResponseEntity.ok(execucao.get()) : ResponseEntity.notFound().build();
	}
	
}
