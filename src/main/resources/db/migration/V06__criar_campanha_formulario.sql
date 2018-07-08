CREATE TABLE campanha_formulario (
	id_campanha BIGINT(20) NOT NULL,	
	id_formulario BIGINT(20) NOT NULL,
	PRIMARY KEY (id_campanha, id_formulario),
	FOREIGN KEY (id_campanha) REFERENCES campanha(id),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO campanha_formulario (id_campanha, id_formulario) values (1, 1);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (1, 2);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (1, 3);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (2, 1);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (3, 4);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (3, 5);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (4, 1);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (4, 3);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (4, 5);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (5, 1);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (5, 2);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (5, 3);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (5, 4);
INSERT INTO campanha_formulario (id_campanha, id_formulario) values (5, 5);

