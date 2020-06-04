CREATE SCHEMA IF NOT EXISTS jdbc_homework;

SET SCHEMA jdbc_homework;

CREATE TABLE IF NOT EXISTS Owner
(
  OWNER_ID        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME      varchar(50),
  SEX       varchar(10),
  CITY      varchar(50),
  STREET    varchar(50),
  POSTCODE  varchar(10),
);

CREATE TABLE IF NOT EXISTS Dog
(
    ID            int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME          varchar(50),
    BREED         varchar(50),
    OWNER_ID      int DEFAULT NULL ,
    FOREIGN KEY (OWNER_ID) REFERENCES OWNER (OWNER_ID)
ON DELETE SET NULL ON UPDATE CASCADE

);
