package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
