CREATE TABLE pergunta (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	tipo VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pergunta (nome, descricao, tipo) values ('Dos itens abaixo, qual você mais gosta?','Dos itens abaixo, qual você mais gosta? - Responda Corretamente','RespostaUnica');
INSERT INTO pergunta (nome, descricao, tipo) values ('Para qual destes times você torce?','Para qual destes times você torce? - Responda Corretamente','RespostaUnica');
INSERT INTO pergunta (nome, descricao, tipo) values ('Descreva sua casa?','Descreva sua casa? - Responda Corretamente','Texto');
INSERT INTO pergunta (nome, descricao, tipo) values ('Cite 3 tipos?','Cite 3 tipos? - Responda Corretamente','Texto');
INSERT INTO pergunta (nome, descricao, tipo) values ('Selecione as cidades?','O que você gosta? - Responda Corretamente','MultiplaEscolha');