DROP TABLE IF EXISTS users CASCADE;

CREATE SEQUENCE users_seq START 4;

CREATE TABLE users (
    id SERIAL NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT false,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_CANDIDAT'
);


INSERT INTO users (username, password, enabled, role)
VALUES
    ('admin', 'admin', false, 'ROLE_ADMIN'),
    ('user', 'user', false, 'ROLE_CANDIDAT'),
    ('ayoub', 'ayoub', false, 'ROLE_RECRUTEUR');
