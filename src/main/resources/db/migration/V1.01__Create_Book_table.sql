/*
generate create query for book that contains the following columns in snake case:
•	id (Primary key)
•	Title
•	Author
•	Publisher
•	ISBN optional
•	Publication Date optional
•	Genres optional
•	Number of Pages optional
•	Tags optional
•	Description optional
•	ContributorId (Foreign key to User table)
•	CurrentKeeperId (Foreign key to User table)
*/
CREATE TABLE book (
    id int NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    isbn VARCHAR(100),
    publication_date DATE,
    genres VARCHAR(100),
    number_of_pages INT,
    tags VARCHAR(100),
    description VARCHAR(1000),
    contributor_id INT NOT NULL,
    current_keeper_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (contributor_id) REFERENCES user(id),
    FOREIGN KEY (current_keeper_id) REFERENCES user(id)
);