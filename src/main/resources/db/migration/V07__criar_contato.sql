CREATE TABLE contato (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	identificador VARCHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;