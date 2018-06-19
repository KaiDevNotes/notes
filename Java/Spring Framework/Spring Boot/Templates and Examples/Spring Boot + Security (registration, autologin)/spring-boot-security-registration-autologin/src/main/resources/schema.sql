
CREATE TABLE user 
(
    id BINARY(16),
    login VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
