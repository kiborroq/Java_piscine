DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id bigint IDENTITY PRIMARY KEY,
    email varchar(100),
    password varchar(100) NOT NULL
);