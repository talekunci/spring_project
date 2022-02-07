INSERT INTO roles(name) VALUES('Admin');
INSERT INTO roles(name) VALUES('User');

--INSERT INTO users(email, password, role_id) VALUES('admin@email.ua', 'qwerty');
--INSERT INTO users(email, password, role_id) VALUES('user@email.ua', 'wasd');
INSERT INTO users(email, password) VALUES('admin@email.ua', '{bcrypt}$2a$10$8QRPk0H6JmuNFWCikekck.TEYrmeYFpiUs1p14uTuQZWRZrfmN8P.');
INSERT INTO users(email, password) VALUES('user@email.ua', '{bcrypt}$2a$10$NY7rVMn4uI5pWezRJuB8B.fLsvUqBV577diGLDXzWZY6uioaGj6bm');

INSERT INTO users_roles(user_id, role_id) VALUES((SELECT id FROM users WHERE email = 'admin@email.ua'), (SELECT id FROM roles WHERE name = 'User'));
INSERT INTO users_roles(user_id, role_id) VALUES((SELECT id FROM users WHERE email = 'admin@email.ua'), (SELECT id FROM roles WHERE name = 'Admin'));
INSERT INTO users_roles(user_id, role_id) VALUES((SELECT id FROM users WHERE email = 'user@email.ua'), (SELECT id FROM roles WHERE name = 'User'));

INSERT INTO producers(name) VALUES('Qualcomm');
INSERT INTO producers(name) VALUES('IBM');
INSERT INTO producers(name) VALUES('Intel');

INSERT INTO products(name, producer_id) VALUES('Snapdragon', (SELECT id FROM producers WHERE name = 'Qualcomm'));
INSERT INTO products(name, producer_id, cost) VALUES('IBM PC', (SELECT id FROM producers WHERE name = 'IBM'), 999.99);
INSERT INTO products(name, producer_id, cost) VALUES('Blue Gene', (SELECT id FROM producers WHERE name = 'IBM'), 99999.991929);
INSERT INTO products(name, producer_id, cost) VALUES('Xeon', (SELECT id FROM producers WHERE name = 'Intel'), 1000);
INSERT INTO products(name, producer_id, cost) VALUES('Nuc', (SELECT id FROM producers WHERE name = 'Intel'), 123.123);
