CREATE TABLE formulario_pergunta (
	id_formulario BIGINT(20) NOT NULL,
	id_pergunta BIGINT(20) NOT NULL,
	PRIMARY KEY (id_formulario, id_pergunta),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id),
	FOREIGN KEY (id_pergunta) REFERENCES pergunta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (1, 1);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (1, 2);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (2, 3);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (3, 1);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (3, 2);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (3, 3);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (4, 4);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (4, 5);
INSERT INTO formulario_pergunta (id_formulario, id_pergunta) values (5, 5);