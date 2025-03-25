CREATE TABLE user_register (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(200),
	senha VARCHAR(200),
	user_type VARCHAR(20) NOT NULL,
	date_creation DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;