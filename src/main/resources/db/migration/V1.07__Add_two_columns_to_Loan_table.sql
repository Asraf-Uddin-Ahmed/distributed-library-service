ALTER TABLE loan
ADD COLUMN status ENUM('PENDING', 'ACCEPTED', 'REJECTED') NOT NULL,
ADD COLUMN from_user_id INT NOT NULL;