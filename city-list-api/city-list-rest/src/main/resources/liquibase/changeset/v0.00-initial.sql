CREATE TABLE FILE
(
    ID   BIGSERIAL PRIMARY KEY,
    UUID UUID UNIQUE
);

CREATE TABLE ROLE
(
    ID   BIGSERIAL PRIMARY KEY,
    UUID UUID UNIQUE
);

CREATE TABLE CITY
(
    ID      BIGSERIAL PRIMARY KEY,
    FILE_ID BIGINT       NOT NULL,
    NAME    VARCHAR(255) NOT NULL,
    FOREIGN KEY (FILE_ID) REFERENCES FILE (ID)
);

CREATE TABLE USE
(
    ID         BIGSERIAL PRIMARY KEY,
    ROLE_ID    BIGINT       NOT NULL,
    UUID       UUID UNIQUE,
    USER_NAME  VARCHAR(50)  NOT NULL,
    PASSWORD   VARCHAR(255) NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME  VARCHAR(255) NOT NULL,
    FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID)
);

