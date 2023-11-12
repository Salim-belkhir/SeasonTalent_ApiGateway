DROP TABLE IF EXISTS users CASCADE;

CREATE SEQUENCE IF NOT EXISTS user_id_seq;


CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL DEFAULT false,
    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER'
);


INSERT INTO users (username, password, enabled)
VALUES
    ('admin', 'admin', false),
    ('user', 'user', false),
    ('ayoub', 'ayoub', false);
