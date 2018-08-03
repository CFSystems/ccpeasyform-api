package br.com.cfsystems.ccpeasyform.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cfsystems.ccpeasyform.model.FormularioPergunta;
import br.com.cfsystems.ccpeasyform.repository.FormularioPerguntaRepository;
import br.com.cfsystems.ccpeasyform.service.FormularioPerguntaService;

@RestController
@RequestMapping("/formularioPergunta")
public class FormularioPerguntaResource {
	
	@Autowired
	private FormularioPerguntaRepository formularioPerguntaRepository;
	
	@Autowired
	private FormularioPerguntaService formularioPerguntaService;
	
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<FormularioPergunta> listar(){
		return formularioPerguntaRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<FormularioPergunta> criar(@Valid @RequestBody FormularioPergunta formularioPergunta, HttpServletResponse response) {
		FormularioPergunta formularioPerguntaSalva = formularioPerguntaRepository.save(formularioPergunta);
		return ResponseEntity.status(HttpStatus.CREATED).body(formularioPerguntaSalva);
	}
	
	@DeleteMapping("/{idFormulario}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void deletar(@PathVariable("idFormulario") Long idFormulario) {
		formularioPerguntaService.deletarFormularioResposta(idFormulario);
	}

}
