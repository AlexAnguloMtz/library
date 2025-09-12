package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}