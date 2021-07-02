DROP TABLE IF EXISTS products;

CREATE TABLE products(
	id bigint IDENTITY PRIMARY KEY,
	name varchar(20) NOT NULL,
	price float NOT NULL
);