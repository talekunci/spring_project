CREATE TABLE roles
  (
     id          SERIAL PRIMARY KEY,
     name        VARCHAR(50) UNIQUE NOT NULL
  );

CREATE TABLE users
  (
    id          SERIAL PRIMARY KEY,
    email       VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(100) NOT NULL,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50)
  );

CREATE TABLE producers
  (
     id          SERIAL PRIMARY KEY,
     name        VARCHAR(50) UNIQUE NOT NULL
  );

CREATE TABLE products
  (
     id         SERIAL PRIMARY KEY,
     name       VARCHAR(50) NOT NULL,
     cost       NUMERIC DEFAULT 0.0,
     producer_id    INTEGER NOT NULL,

     CONSTRAINT producer_id_fk FOREIGN KEY (producer_id) REFERENCES producers(id)
  );

CREATE TABLE users_roles
  (
     user_id         INTEGER NOT NULL,
     role_id         INTEGER NOT NULL,

     CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id),
     CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES roles(id)
  );