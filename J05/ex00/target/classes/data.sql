INSERT
INTO
	users(login, password)
VALUES
	('dmitriy', 'dmitriy'),
	('andrey', 'andrey'),
	('anton', 'anton'),
	('olesya', 'olesya'),
	('alina', 'alina');


INSERT
INTO
	rooms(name, owner_id)
VALUES
	('girls', 4),
	('boys', 1),
	('all', 1),
	('name start with a', 5),
	('football', 2);


INSERT
INTO user_room(user_id, room_id)
VALUES
	(1, 2), (1, 3), (1, 5),
	(2, 2), (2, 3), (2, 4), (2, 5),
	(3, 2), (3, 3), (3, 4),
	(4, 1), (4, 2),
	(5, 1), (5, 3), (5, 4);


INSERT
INTO messages(author_id, room_id, text, date)
VALUES
	(1, 2, 'boys, hello from dmitriy', current_timestamp),
	(1, 3, 'all, hello from dmitriy', current_timestamp),
	(1, 5, 'football, hello from dmitriy', current_timestamp),

	(2, 2, 'boys, hello from andrey', current_timestamp),
	(2, 3, 'all, hello from andrey', current_timestamp),
	(2, 4, 'name start with a, hello from andrey', current_timestamp),
	(2, 5, 'football, hello from andrey', current_timestamp),

	(3, 2, 'boys, hello from anton', current_timestamp),
	(3, 3, 'all, hello from anton', current_timestamp),
	(3, 4, 'name start with a, hello from anton', current_timestamp),

	(4, 1, 'girls, hello from olesya', current_timestamp),
	(4, 3, 'all, hello from olesya', current_timestamp),

	(5, 1, 'girls, hello from alina', current_timestamp),
	(5, 3, 'all, hello from alina', current_timestamp),
	(5, 4, 'name start with a, hello from alina', current_timestamp);
