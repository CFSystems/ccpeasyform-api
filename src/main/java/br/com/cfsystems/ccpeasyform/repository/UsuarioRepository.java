package br.com.cfsystems.ccpeasyform.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	
}
