DROP TABLE usuario_permissao;
DROP TABLE usuario;
DROP TABLE permissao;

CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	matricula VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, matricula, email, senha, ativo) values ('Administrador CCP Easy Form', 'adminccp', 'admin@ccpeasyform.com', '$2a$10$116/2lKS1LBUnqeH7OL9muiVrNnP6VW0Z9ehPFJqeVCz9tdEG/8gS', true);

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