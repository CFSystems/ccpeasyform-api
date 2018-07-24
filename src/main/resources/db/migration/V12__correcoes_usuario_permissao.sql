DROP TABLE usuario_permissao;
DROP TABLE permissao;

ALTER TABLE usuario
	ADD COLUMN matricula VARCHAR(255) NOT NULL AFTER nome,
	ADD COLUMN ativo BOOLEAN NOT NULL AFTER senha;
		
UPDATE usuario
	SET
		nome = 'Administrador',
		matricula = 'adminccp',
		email = 'admin@ccpeasyform.com',
		senha = '$2a$10$116/2lKS1LBUnqeH7OL9muiVrNnP6VW0Z9ehPFJqeVCz9tdEG/8gS',
		ativo = true
	WHERE id = 1;

DELETE FROM usuario
	WHERE id = 2;

CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (descricao) values ('ADMINISTRADOR');
INSERT INTO permissao (descricao) values ('SUPERVISOR');
INSERT INTO permissao (descricao) values ('OPERADOR');

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);