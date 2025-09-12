package com.unison.practicas.desarrollo.core.configuration.seeder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unison.practicas.desarrollo.core.model.Book;
import com.unison.practicas.desarrollo.core.model.BookAuthor;
import com.unison.practicas.desarrollo.core.model.BookCategory;
import com.unison.practicas.desarrollo.core.repository.AuthorRepository;
import com.unison.practicas.desarrollo.core.repository.BookCategoryRepository;
import com.unison.practicas.desarrollo.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Profile({"dev"})
public class AuthorsAndBooksSeeder {

    @Value("classpath:dev/data/authors_and_books.json")
    private Resource authorsAndBooksResource;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    public AuthorsAndBooksSeeder(ObjectMapper objectMapper, AuthorRepository authorRepository, BookCategoryRepository bookCategoryRepository, BookRepository bookRepository) {
        this.objectMapper = objectMapper;
        this.authorRepository = authorRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookRepository = bookRepository;
    }

    public void seed() throws IOException {
        if (authorRepository.count() > 0 || bookRepository.count() > 0) {
            return;
        }

        // Read authors and books
        Set<AuthorWithBooksJson> authorsWithBooks = objectMapper.readValue(
                authorsAndBooksResource.getInputStream(),
                new TypeReference<>() {}
        );

        // Sort by last name and first name
        Set<AuthorWithBooksJson> sortedSet = authorsWithBooks.stream()
                .sorted(Comparator.comparing((AuthorWithBooksJson a) -> a.author().lastName())
                        .thenComparing(a -> a.author().firstName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // Extract all categories
        Set<String> categories = sortedSet.stream()
                .flatMap(authorWithBooks -> authorWithBooks.books().stream())
                .flatMap(book -> book.categories().stream())
                .collect(Collectors.toSet());

        // Make sure all categories exist in the database, insert those that do not exist
        Set<String> existingCategories = bookCategoryRepository.findAll().stream()
                .map(BookCategory::getName)
                .collect(Collectors.toSet());

        List<BookCategory> newCategories = categories.stream()
                .filter(catName -> !existingCategories.contains(catName))
                .map(this::categoryWithName)
                .collect(Collectors.toList());

        if (!newCategories.isEmpty()) {
            bookCategoryRepository.saveAll(newCategories);
        }

        List<BookAuthor> authors = authorsWithBooks.stream()
                .map(this::toAuthor)
                .collect(Collectors.toList());

        List<BookAuthor> savedAuthors = authorRepository.saveAll(authors);
        List<BookCategory> allCategories = bookCategoryRepository.findAll();

        var counter = new AtomicInteger(1);

        List<Book> books = authorsWithBooks.stream()
            .flatMap(authorWithBooksJson -> authorWithBooksJson.books().stream()
                    .map((bookJson) -> toBook(counter.getAndIncrement(), bookJson, authorWithBooksJson.author(), savedAuthors, allCategories))
            ).collect(Collectors.toList());

        bookRepository.saveAll(books);
    }

    private BookAuthor toAuthor(AuthorWithBooksJson authorWithBooks) {
        var author = new BookAuthor();
        author.setFirstName(authorWithBooks.author.firstName());
        author.setLastName(authorWithBooks.author.lastName());
        return author;
    }

    private BookCategory categoryWithName(String name) {
        var category = new BookCategory();
        category.setName(name);
        return category;
    }

    private Book toBook(Integer seed, BookJson bookJson, AuthorJson authorJson, List<BookAuthor> savedAuthors, List<BookCategory> allCategories) {
        var book = new Book();
        book.setTitle(bookJson.title());
        book.setPublishedYear(bookJson.publishedYear());
        book.setIsbn(makeIsbn(seed));

        savedAuthors.stream()
                .filter(a -> a.getFirstName().trim().equalsIgnoreCase(authorJson.firstName()) &&
                        a.getLastName().trim().equalsIgnoreCase(authorJson.lastName()))
                .findFirst()
                .ifPresent(a -> book.getAuthors().add(a));

        bookJson.categories().forEach(catName ->
                allCategories.stream()
                        .filter(c -> c.getName().trim().equalsIgnoreCase(catName))
                        .findFirst()
                        .ifPresent(book.getCategories()::add)
        );

        return book;
    }

    private String makeIsbn(int seed) {
        String lastPart = String.format("%03d", seed);
        return "978-1-234-" + lastPart + "-0";
    }
    private record AuthorWithBooksJson(
            AuthorJson author,
            Set<BookJson> books
    ) {}

    private record AuthorJson(
            String firstName,
            String lastName
    ) {}

    private record BookJson(
            String title,
            Integer publishedYear,
            String isbn,
            Set<String> categories
    ) {}
}
