DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;


CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT false
);


CREATE TABLE authorities (
    authority_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL REFERENCES users(username),
    authority VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'
);

INSERT INTO users (username, password, enabled)
VALUES
    ('admin', 'admin', false),
    ('user', 'user', false),
    ('ayoub', 'ayoub', false);

INSERT INTO authorities (username, authority)
VALUES
    ('admin', 'ROLE_ADMIN'),
    ('user', 'ROLE_USER'),
    ('ayoub', 'ROLE_USER');