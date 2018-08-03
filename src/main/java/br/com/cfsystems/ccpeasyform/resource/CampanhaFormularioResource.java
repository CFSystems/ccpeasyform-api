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

import br.com.cfsystems.ccpeasyform.model.CampanhaFormulario;
import br.com.cfsystems.ccpeasyform.repository.CampanhaFormularioRepository;
import br.com.cfsystems.ccpeasyform.service.CampanhaFormularioService;

@RestController
@RequestMapping("/campanhaFormulario")
public class CampanhaFormularioResource {
	
	@Autowired
	private CampanhaFormularioRepository campanhaFormularioRepository;
	
	@Autowired
	private CampanhaFormularioService campanhaFormularioService;
	
	
	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMINISTRADOR','SUPERVISOR','OPERADOR') and #oauth2.hasScope('read')")
	public List<CampanhaFormulario> listar(){
		return campanhaFormularioRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<CampanhaFormulario> criar(@Valid @RequestBody CampanhaFormulario campanhaFormulario, HttpServletResponse response) {
		CampanhaFormulario campanhaFormularioSalva = campanhaFormularioRepository.save(campanhaFormulario);
		return ResponseEntity.status(HttpStatus.CREATED).body(campanhaFormularioSalva);
	}
	
	@DeleteMapping("/{idCampanha}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void deletar(@PathVariable("idCampanha") Long idCampanha) {
		campanhaFormularioService.deletarCampanhaFormulario(idCampanha);
	}

}
