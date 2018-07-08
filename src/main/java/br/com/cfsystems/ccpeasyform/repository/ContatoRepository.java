package br.com.cfsystems.ccpeasyform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cfsystems.ccpeasyform.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
