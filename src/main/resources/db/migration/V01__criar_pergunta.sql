CREATE TABLE pergunta (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	tipo VARCHAR(50) NOT NULL,
	em_uso BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pergunta (nome, descricao, tipo, em_uso) values ('Dos itens abaixo, qual você mais gosta?','Dos itens abaixo, qual você mais gosta? - Responda Corretamente','RespostaUnica', false);
INSERT INTO pergunta (nome, descricao, tipo, em_uso) values ('Para qual destes times você torce?','Para qual destes times você torce? - Responda Corretamente','RespostaUnica', false);
INSERT INTO pergunta (nome, descricao, tipo, em_uso) values ('Descreva sua casa?','Descreva sua casa? - Responda Corretamente','Texto', false);
INSERT INTO pergunta (nome, descricao, tipo, em_uso) values ('Cite 3 tipos?','Cite 3 tipos? - Responda Corretamente','Texto', false);
INSERT INTO pergunta (nome, descricao, tipo, em_uso) values ('Selecione as cidades?','O que você gosta? - Responda Corretamente','MultiplaEscolha', false);