CREATE TABLE execucao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_contato BIGINT(20) NOT NULL,
	id_campanha BIGINT(20) NOT NULL,
	id_formulario BIGINT(20) NOT NULL,
	id_usuario BIGINT(20) NOT NULL,
	data_execucao DATE NOT NULL,
	FOREIGN KEY (id_contato) REFERENCES contato(id),
	FOREIGN KEY (id_campanha) REFERENCES campanha(id),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO execucao (id_contato, id_campanha, id_formulario, id_usuario, data_execucao) values (1, 2, 3, 1, '2018-07-01');
INSERT INTO execucao (id_contato, id_campanha, id_formulario, id_usuario, data_execucao) values (2, 3, 4, 1, '2018-07-02');
INSERT INTO execucao (id_contato, id_campanha, id_formulario, id_usuario, data_execucao) values (3, 1, 5, 1, '2018-07-03');
INSERT INTO execucao (id_contato, id_campanha, id_formulario, id_usuario, data_execucao) values (5, 4, 1, 1, '2018-07-04');
INSERT INTO execucao (id_contato, id_campanha, id_formulario, id_usuario, data_execucao) values (4, 5, 2, 1, '2018-07-05');