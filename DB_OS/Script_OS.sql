CREATE DATABASE sistema_os;

USE sistema_os;

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    email VARCHAR(100),
    cpf VARCHAR(14),
    telefone VARCHAR(20)
);


select * from cliete