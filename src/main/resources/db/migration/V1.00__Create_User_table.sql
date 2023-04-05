/*
generate create query for table user that contains the following columns:
id int NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
password VARCHAR(100) NOT NULL,
user_status VARCHAR(45) NOT NULL,
last_login DATETIME NULL,
wrong_password_attempt INT NULL,
last_wrong_password_attempt DATETIME NULL,
creation_time DATETIME NOT NULL,
update_time DATETIME NOT NULL,
phone (varchar(15))
city (varchar(255))
address (varchar(255))
*/
CREATE TABLE user (
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_status VARCHAR(45) NOT NULL,
    last_login DATETIME NULL,
    wrong_password_attempt INT NULL,
    last_wrong_password_attempt DATETIME NULL,
    creation_time DATETIME NOT NULL,
    update_time DATETIME NOT NULL,
    phone varchar(15),
    city varchar(100),
    address varchar(255),
    PRIMARY KEY (id),
    UNIQUE INDEX email_UNIQUE (email ASC)
);