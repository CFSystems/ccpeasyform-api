CREATE TABLE opcao (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	id_pergunta BIGINT(20) NOT NULL,
	FOREIGN KEY (id_pergunta) REFERENCES pergunta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO opcao (nome, id_pergunta) values ('Futebol', 1);
INSERT INTO opcao (nome, id_pergunta) values ('Dança', 1);
INSERT INTO opcao (nome, id_pergunta) values ('Filme', 1);
INSERT INTO opcao (nome, id_pergunta) values ('Paraná', 2);
INSERT INTO opcao (nome, id_pergunta) values ('Atlético', 2);
INSERT INTO opcao (nome, id_pergunta) values ('Coritiba', 2);
INSERT INTO opcao (nome, id_pergunta) values ('Curitiba', 5);
INSERT INTO opcao (nome, id_pergunta) values ('Araucária', 5);
INSERT INTO opcao (nome, id_pergunta) values ('Pinhais', 5);
INSERT INTO opcao (nome, id_pergunta) values ('Colombo', 5);