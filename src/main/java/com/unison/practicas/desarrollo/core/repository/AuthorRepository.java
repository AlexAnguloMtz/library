package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<BookAuthor, Integer> {
}