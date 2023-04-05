/*
generate create query for Reservation table that contains the following columns in snake case:
•	ID (Primary key)
•	BookID (Foreign key to Book table)
•	UserID (Foreign key to User table)
•	Reservation Date
*/
CREATE TABLE reservation (
  id INT NOT NULL AUTO_INCREMENT,
  book_id INT NOT NULL,
  user_id INT NOT NULL,
  reservation_date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (book_id) REFERENCES book(id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);
