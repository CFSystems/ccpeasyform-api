CREATE TABLE execucao_resposta (
	id_execucao BIGINT(20) NOT NULL,
	id_pergunta BIGINT(20) NOT NULL,
	resposta VARCHAR(1000) NOT NULL,
	PRIMARY KEY (id_execucao, id_pergunta),
	FOREIGN KEY (id_execucao) REFERENCES execucao(id),
	FOREIGN KEY (id_pergunta) REFERENCES pergunta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (1, 1, 'Teste');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (1, 2, 'Paraná');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (1, 3, 'Futebol');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (2, 1, 'Teste');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (2, 2, 'Atlético');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (2, 3, 'Volei');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (2, 4, 'Não sei');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (3, 5, 'Talvez');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (4, 3, 'Sim');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (4, 5, 'Sim');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (5, 1, 'Teste');
INSERT INTO execucao_resposta (id_execucao, id_pergunta, resposta) values (5, 3, 'Dança');