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

INSERT INTO atendimento (id_contato, id_campanha, id_formulario, id_usuario, data_atendimento) values (1, 2, 3, 1, '2018-07-01');
INSERT INTO atendimento (id_contato, id_campanha, id_formulario, id_usuario, data_atendimento) values (2, 3, 4, 1, '2018-07-01');
INSERT INTO atendimento (id_contato, id_campanha, id_formulario, id_usuario, data_atendimento) values (3, 4, 5, 1, '2018-07-01');
INSERT INTO atendimento (id_contato, id_campanha, id_formulario, id_usuario, data_atendimento) values (4, 5, 1, 1, '2018-07-01');
INSERT INTO atendimento (id_contato, id_campanha, id_formulario, id_usuario, data_atendimento) values (5, 6, 2, 1, '2018-07-01');