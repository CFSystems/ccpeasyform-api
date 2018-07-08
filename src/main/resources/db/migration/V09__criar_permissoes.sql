CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_PERGUNTA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_PERGUNTA');
INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_FORMULARIO');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_FORMULARIO');
INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_CAMPANHA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_CAMPANHA');
INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_ATENDIMENTO');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_ATENDIMENTO');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);

-- maria
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 8);