create table clients
(
    id INTEGER UNSIGNED PRIMARY KEY UNIQUE AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);