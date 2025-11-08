CREATE DATABASE IF NOT EXISTS cinema_api;
USE cinema_api;

CREATE TABLE filme (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    sinopse TEXT,
    genero VARCHAR(100),
    ano_lancamento INT NOT NULL
);

CREATE TABLE analise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    texto TEXT,
    nota DOUBLE NOT NULL,
    filme_id BIGINT,
    CONSTRAINT fk_analise_filme FOREIGN KEY (filme_id)
        REFERENCES filme(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);