/*
Generate create query for audit table to keep track of who added or edited the book, as well as the date and time of the change.
Use enum for action.
*/
CREATE TABLE book_audit (
    id int NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    action ENUM('ADD', 'EDIT') NOT NULL,
    contributor_id INT NOT NULL,
    action_time DATETIME NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (contributor_id) REFERENCES user(id)
);