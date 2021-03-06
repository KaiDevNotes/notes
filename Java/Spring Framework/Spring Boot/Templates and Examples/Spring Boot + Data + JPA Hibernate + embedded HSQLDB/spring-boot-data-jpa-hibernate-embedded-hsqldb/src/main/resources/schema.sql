CREATE TABLE role (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1),
    login VARCHAR(512) NOT NULL,
    password VARCHAR(64) NOT NULL,
    role_id INTEGER NULL,
    first_name VARCHAR(512) NULL,
    last_name VARCHAR(512) NULL,
    PRIMARY KEY (id)
);

ALTER TABLE user ADD CONSTRAINT user_ibfk_1 FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE SET NULL ON UPDATE CASCADE;