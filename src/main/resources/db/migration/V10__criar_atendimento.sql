CREATE TABLE atendimento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_contato BIGINT(20) NOT NULL,
	id_campanha BIGINT(20) NOT NULL,
	id_formulario BIGINT(20) NOT NULL,
	id_usuario BIGINT(20) NOT NULL,
	data_atendimento DATE NOT NULL,
	FOREIGN KEY (id_contato) REFERENCES contato(id),
	FOREIGN KEY (id_campanha) REFERENCES campanha(id),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;