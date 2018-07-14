CREATE TABLE campanha_formulario (
	id_campanha BIGINT(20) NOT NULL,	
	id_formulario BIGINT(20) NOT NULL,
	PRIMARY KEY (id_campanha, id_formulario),
	FOREIGN KEY (id_campanha) REFERENCES campanha(id),
	FOREIGN KEY (id_formulario) REFERENCES formulario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;