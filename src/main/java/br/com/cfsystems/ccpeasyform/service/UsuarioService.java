package br.com.cfsystems.ccpeasyform.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cfsystems.ccpeasyform.model.Usuario;
import br.com.cfsystems.ccpeasyform.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String senhaCrypt;
	
	public Usuario salvar(Usuario usuario) {
		this.senhaCrypt = encoder.encode(usuario.getSenha());
		usuario.setSenha(this.senhaCrypt);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarUsuarioPeloId(id);
		
		if(usuario.getSenha().equals(usuarioSalvo.getSenha())) {
			BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
			return usuarioRepository.save(usuarioSalvo);
		} else {
			BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
			this.senhaCrypt = encoder.encode(usuarioSalvo.getSenha());
			usuarioSalvo.setSenha(this.senhaCrypt);
			return usuarioRepository.save(usuarioSalvo);
		}
	}
	
	public Usuario buscarUsuarioPeloId(Long id) {
		Optional<Usuario> usuarioSalvo = usuarioRepository.findById(id);
		if (!usuarioSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo.get();
	}

	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Usuario usuarioSalvo = buscarUsuarioPeloId(id);
		usuarioSalvo.setAtivo(ativo);
		usuarioRepository.save(usuarioSalvo);
	}
		
}
