CREATE TABLE book_category_assignment (
    book_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (book_id, category_id),
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES book_category(id) ON DELETE CASCADE
);