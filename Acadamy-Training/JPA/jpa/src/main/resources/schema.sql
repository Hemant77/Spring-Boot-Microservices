DROP TABLE product IF EXISTS;
DROP TABLE customer IF EXISTS;
DROP TABLE supplier IF EXISTS;

CREATE TABLE product(id integer identity primary key, name varchar(15));
CREATE TABLE customer(id integer identity primary key, name varchar(15));
CREATE TABLE supplier(id integer identity primary key, name varchar(15));