package com.unison.practicas.desarrollo.core.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer copyId;
    private Integer userId;
    private Instant loanDate;
    private Instant dueDate;
    private Instant returnDate;

    public Integer getId() {
        return id;
    }

    public Integer getCopyId() {
        return copyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Instant getLoanDate() {
        return loanDate;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLoanDate(Instant loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLoan bookLoan)) return false;
        return id != null && id.equals(bookLoan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "id=" + id +
                ", copyId=" + copyId +
                ", userId=" + userId +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

