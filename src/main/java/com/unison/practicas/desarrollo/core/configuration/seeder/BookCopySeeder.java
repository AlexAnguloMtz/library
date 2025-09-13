package com.unison.practicas.desarrollo.core.configuration.seeder;

import com.unison.practicas.desarrollo.core.model.Book;
import com.unison.practicas.desarrollo.core.model.BookCopy;
import com.unison.practicas.desarrollo.core.repository.BookCopyRepository;
import com.unison.practicas.desarrollo.core.repository.BookRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@Profile({"dev", "test"})
public class BookCopySeeder {

    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    public BookCopySeeder(BookRepository bookRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
    }

    public void seed() {
        if (bookCopyRepository.count() > 0) {
            return;
        }

        var copies = bookRepository.findAll().stream()
                .flatMap(this::bookCopiesStream)
                .toList();

        bookCopyRepository.saveAll(copies);
    }

    private Stream<BookCopy> bookCopiesStream(Book book) {
        var count = ThreadLocalRandom.current().nextInt(0, 21);
        return IntStream.range(0, count)
                .mapToObj(i -> createBookCopy(book));
    }

    private BookCopy createBookCopy(Book book) {
        var copy = new BookCopy();
        copy.setBookId(book.getId());
        copy.setAddedAt(Instant.now());
        return copy;
    }

}