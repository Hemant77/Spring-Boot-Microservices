DROP TABLE product IF EXISTS;
DROP TABLE customer IF EXISTS;
DROP TABLE supplier IF EXISTS;

CREATE TABLE product(id integer identity primary key, productname varchar(15), price float);
CREATE TABLE customer(id integer identity primary key, customername varchar(15), productname varchar(15));
CREATE TABLE supplier(id integer identity primary key, suppliername varchar(15), productname varchar(15));