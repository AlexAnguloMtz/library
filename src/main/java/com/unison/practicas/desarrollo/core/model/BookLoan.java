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
    private Instant loanDate;
    private Instant dueDate;
    private Instant returnDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public Integer getCopyId() {
        return copyId;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setCopyId(Integer copyId) {
        this.copyId = copyId;
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
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public boolean isActiveLoan() {
        return returnDate == null;
    }

}