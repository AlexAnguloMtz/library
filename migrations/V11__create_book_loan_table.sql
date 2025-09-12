CREATE TABLE book_loan (
    id SERIAL PRIMARY KEY,
    copy_id INT NOT NULL,
    user_id INT NOT NULL,
    loan_date TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMPTZ NOT NULL,
    return_date TIMESTAMPTZ,
    FOREIGN KEY (copy_id) REFERENCES book_copy(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE
);