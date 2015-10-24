DROP DATABASE if EXISTS Chess;
CREATE DATABASE Chess;
USE Chess;
CREATE TABLE Users (
	UserID int(10) primary key not null auto_increment,
	username varchar(50) not null,
	fname varchar(50) not null,
	flame varchar(50) not null,
	won int(5) not null,
	lost int(5) not null,
	ip varchar(15) not null
);