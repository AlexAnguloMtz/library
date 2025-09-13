package com.unison.practicas.desarrollo.core.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Book extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer publishedYear;
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_category_assignment",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<BookCategory> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author_assignment",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @OrderColumn(name = "author_order")
    private List<BookAuthor> authors = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<BookAuthor> getAuthors() {
        return authors;
    }

    public Set<BookCategory> getCategories() {
        return categories;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return id != null && id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }

}
