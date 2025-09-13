CREATE TABLE book_author_assignment (
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    author_order SMALLINT NOT NULL DEFAULT 0,
    PRIMARY KEY (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES book_author(id) ON DELETE CASCADE,
    CONSTRAINT unique_book_author_order UNIQUE (book_id, author_order),
    CONSTRAINT check_author_order_positive CHECK (author_order >= 0)
);