CREATE TABLE patients (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL unique,
                         phone VARCHAR(20) NOT NULL,
                         cpf VARCHAR(20) NOT NULL unique,
                         street VARCHAR(100) NOT NULL,
                         number VARCHAR(20),
                         complement VARCHAR(100),
                         neighborhood VARCHAR(100) NOT NULL,
                         city VARCHAR(100) NOT NULL,
                         state VARCHAR(2) NOT NULL,
                         postal_code VARCHAR(9) NOT NULL,
                         active BOOLEAN NOT NULL
);
