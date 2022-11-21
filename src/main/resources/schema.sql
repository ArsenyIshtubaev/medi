CREATE TABLE IF NOT EXISTS users
(
    user_id   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name VARCHAR(128) NOT NULL,
    email     VARCHAR(128) NOT NULL,
    UNIQUE (email)
);