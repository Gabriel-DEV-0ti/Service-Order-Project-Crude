CREATE DATABASE sistema_os;

USE sistema_os;

CREATE TABLE cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) not null,
    email VARCHAR(100) not null,
    cpf VARCHAR(14) not null,
    telefone VARCHAR(20) not null,
    aparelho VARCHAR(255) not null
);


select * from cliente