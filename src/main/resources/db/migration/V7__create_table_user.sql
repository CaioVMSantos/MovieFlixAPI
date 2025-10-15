CREATE TABLE users(
    id serial PRIMARY KEY,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);