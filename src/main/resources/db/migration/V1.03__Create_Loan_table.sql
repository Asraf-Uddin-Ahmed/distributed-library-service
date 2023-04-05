/*
generate create query for loan table that contains the following columns in snake case:
•	ID (Primary key)
•	BookID (Foreign key to Book table)
•	UserID (Foreign key to User table)
•	Loan Date
•	Due Date
*/
CREATE TABLE loan (
    id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    loan_date DATE NOT NULL,
    due_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);