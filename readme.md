# Example using Play Framework and Jooq with Scala

Configured to use postgres running in a local docker container. Using this container: https://hub.docker.com/_/postgres (postgres:latest).

## Database setup
```
CREATE DATABASE jooq default character set utf8 default collate utf8_bin;

CREATE ROLE jooquser WITH LOGIN PASSWORD 'jooquser'

GRANT ALL PRIVILEGES ON DATABASE jooq TO jooquser;

CREATE TABLE CUSTOMER(
   ID SERIAL NOT NULL,
   NAME VARCHAR (20)     NOT NULL,
   AGE  INT              NOT NULL,
   ADDRESS  CHAR (25) ,
   SALARY   DECIMAL (18, 2),       
   PRIMARY KEY (ID)
);

CREATE TABLE COMPANY(
   ID SERIAL NOT NULL,
   NAME VARCHAR (20)     NOT NULL,
   ADDRESS  CHAR (25) , 
   PRIMARY KEY (ID)
);
```