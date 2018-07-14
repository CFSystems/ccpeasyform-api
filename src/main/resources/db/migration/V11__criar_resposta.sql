CREATE TABLE resposta (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	id_atendimento BIGINT(20) NOT NULL,
	id_pergunta BIGINT(20) NOT NULL,
	resposta VARCHAR(1000) NOT NULL,
	FOREIGN KEY (id_atendimento) REFERENCES atendimento(id),
	FOREIGN KEY (id_pergunta) REFERENCES pergunta(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;