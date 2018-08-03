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

import br.com.cfsystems.ccpeasyform.dto.RespostaEstatisticaFormulario;
import br.com.cfsystems.ccpeasyform.event.RecursoCriadoEvent;
import br.com.cfsystems.ccpeasyform.model.Resposta;
import br.com.cfsystems.ccpeasyform.repository.RespostaRepository;

@RestController
@RequestMapping("/resposta")
public class RespostaResource {

	@Autowired
	private RespostaRepository respostaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<Resposta> listar() {
		return respostaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Resposta> buscarPeloId(@PathVariable Long id) {
		Optional<Resposta> resposta = respostaRepository.findById(id);
		return resposta.isPresent() ? ResponseEntity.ok(resposta.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/estatisticas/por-formulario/{idCampanha}/{idFormulario}/{idPergunta}")
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR') and #oauth2.hasScope('read')")
	public List<RespostaEstatisticaFormulario> porFormulario(@PathVariable("idCampanha") Long idCampanha,
			@PathVariable("idFormulario") Long idFormulario, @PathVariable("idPergunta") Long idPergunta) {
		return this.respostaRepository.porFormulario(idCampanha, idFormulario, idPergunta);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Resposta> criar(@Valid @RequestBody Resposta resposta, HttpServletResponse response) {
		Resposta respostaSalva = respostaRepository.save(resposta);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, respostaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(respostaSalva);
	}

}
