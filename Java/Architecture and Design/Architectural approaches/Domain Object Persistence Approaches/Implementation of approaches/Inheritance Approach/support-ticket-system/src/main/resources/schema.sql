
CREATE TABLE user 
(
    id BINARY(16),
    login VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE ticket 
(
    id BINARY(16),
    issue_description VARCHAR(512) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    resolution_date TIMESTAMP NULL,
    status VARCHAR(20) NOT NULL,
    submitter_id BINARY(16) NULL,
    PRIMARY KEY (id)
);


CREATE TABLE message
(
    id BINARY(16),
    message_text VARCHAR(512) NOT NULL,
    date TIMESTAMP NOT NULL,
    party VARCHAR(20) NOT NULL,
    ticket_id BINARY(16) NULL,
    PRIMARY KEY (id)
);


ALTER TABLE ticket ADD CONSTRAINT ticket_ibfk_1 FOREIGN KEY (submitter_id) REFERENCES user (id) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE message ADD CONSTRAINT message_ibfk_1 FOREIGN KEY (ticket_id) REFERENCES ticket (id) ON DELETE CASCADE ON UPDATE CASCADE;