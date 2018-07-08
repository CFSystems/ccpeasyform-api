CREATE TABLE formulario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO formulario (nome, ativo) values ('Formulário 1', false);
INSERT INTO formulario (nome, ativo) values ('Formulário 2', true);
INSERT INTO formulario (nome, ativo) values ('Formulário 3', false);
INSERT INTO formulario (nome, ativo) values ('Formulário 4', true);
INSERT INTO formulario (nome, ativo) values ('Formulário 5', false);