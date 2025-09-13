package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
}
