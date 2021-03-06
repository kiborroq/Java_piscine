CREATE TABLE IF NOT EXISTS users
(
	id BIGSERIAL PRIMARY KEY,
	login VARCHAR(20) UNIQUE NOT NULL,
	password VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS rooms
(
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	owner_id BIGINT REFERENCES users(id) NOT NULL,
	UNIQUE(owner_id, name)
);

CREATE TABLE IF NOT EXISTS user_room
(
	user_id BIGINT REFERENCES users(id),
	room_id BIGINT REFERENCES rooms(id),
	PRIMARY KEY(user_id, room_id)
);

CREATE TABLE IF NOT EXISTS messages
(
	id BIGSERIAL PRIMARY KEY,
	author_id BIGINT REFERENCES users(id) NOT NULL,
	room_id BIGINT REFERENCES rooms(id) NOT NULL,
	text TEXT NOT NULL,
	date TIMESTAMP
);
