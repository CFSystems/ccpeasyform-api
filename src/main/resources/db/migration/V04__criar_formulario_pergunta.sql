CREATE TABLE formulario_pergunta (
	id_formulario BIGINT(20) NOT NULL,
	id_pergunta BIGINT(20) NOT NULL,
	PRIMARY KEY (id_formulario, id_pergunta),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id),
	FOREIGN KEY (id_pergunta) REFERENCES pergunta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;