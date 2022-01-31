INSERT INTO roles(name) VALUES('Admin');
INSERT INTO roles(name) VALUES('User');

--INSERT INTO users(email, password, role_id) VALUES('admin@email.ua', 'qwerty', (select id from roles where name = 'Admin'));
--INSERT INTO users(email, password, role_id) VALUES('user@email.ua', 'wasd', (select id from roles where name = 'User'));
INSERT INTO users(email, password, role_id) VALUES('admin@email.ua', '{bcrypt}$2a$10$8QRPk0H6JmuNFWCikekck.TEYrmeYFpiUs1p14uTuQZWRZrfmN8P.', (select id from roles where name = 'Admin'));
INSERT INTO users(email, password, role_id) VALUES('user@email.ua', '{bcrypt}$2a$10$NY7rVMn4uI5pWezRJuB8B.fLsvUqBV577diGLDXzWZY6uioaGj6bm', (select id from roles where name = 'User'));

INSERT INTO producers(name) VALUES('Qualcomm');
INSERT INTO producers(name) VALUES('IBM');
INSERT INTO producers(name) VALUES('Intel');

INSERT INTO products(name, producer_id) VALUES('Snapdragon', 1);
INSERT INTO products(name, producer_id, cost) VALUES('IBM PC', 2, 999.99);
INSERT INTO products(name, producer_id, cost) VALUES('Blue Gene', 2, 99999.991929);
INSERT INTO products(name, producer_id, cost) VALUES('Xeon', 3, 1000);
INSERT INTO products(name, producer_id, cost) VALUES('Nuc', 3, 123.123);
