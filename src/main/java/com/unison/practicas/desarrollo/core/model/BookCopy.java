package com.unison.practicas.desarrollo.core.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class BookCopy extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bookId;
    private Instant addedAt;

    public Integer getId() {
        return id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopy bookCopy)) return false;
        return id != null && id.equals(bookCopy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", addedAt=" + addedAt +
                '}';
    }

}

