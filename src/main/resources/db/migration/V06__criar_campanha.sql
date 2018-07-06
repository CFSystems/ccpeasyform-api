CREATE TABLE campanha (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL,
	cliente VARCHAR(255) NOT NULL,
	data_inicio DATE,
	data_termino DATE,
	status VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 1', 'Cliente 1', '2018-07-01', '2018-07-05', 'Concluída');
INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 2', 'Cliente 2', '2018-07-01', null, 'Em Andamento');
INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 3', 'Cliente 3', null, null, 'Pendente');
INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 4', 'Cliente 4', '2018-07-01', null, 'Em Andamento');
INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 5', 'Cliente 5', '2018-07-02', '2018-07-04', 'Concluída');
INSERT INTO campanha (nome, cliente, data_inicio, data_termino, status) values ('Campanha 6', 'Cliente 6', null, null, 'Pendente');