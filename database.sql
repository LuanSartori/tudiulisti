CREATE DATABASE tudiulisti;
USE tudiulisti;

CREATE TABLE Tarefas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NULL,
    prazo DATETIME DEFAULT NULL,
    status TINYINT(1) NOT NULL DEFAULT 0
);
