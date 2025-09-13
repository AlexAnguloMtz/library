package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
}
