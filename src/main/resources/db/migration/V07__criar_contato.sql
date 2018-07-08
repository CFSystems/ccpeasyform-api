CREATE TABLE contato (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	identificador VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contato (nome, cpf, identificador) values ('José da Silva', 12345678910, 123);
INSERT INTO contato (nome, cpf, identificador) values ('Maria da Silva', 12345678911, 456);
INSERT INTO contato (nome, cpf, identificador) values ('João da Silva', 12345678912, 789);
INSERT INTO contato (nome, cpf, identificador) values ('Cleber da Silva', 12345678913, 987);
INSERT INTO contato (nome, cpf, identificador) values ('Jonatan da Silva', 12345678914, 654);
INSERT INTO contato (nome, cpf, identificador) values ('Fabio da Silva', 12345678915, 321);
INSERT INTO contato (nome, cpf, identificador) values ('Edir da Silva', 12345678916, 159);
INSERT INTO contato (nome, cpf, identificador) values ('Carlos da Silva', 12345678917, 357);