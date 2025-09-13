package com.unison.practicas.desarrollo.core.configuration.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev"})
public class DevelopmentSeeder implements CommandLineRunner {

    private final UserSeeder userSeeder;
    private final AuthorsAndBooksSeeder authorsAndBooksSeeder;
    private final BookCopySeeder bookCopySeeder;
    private final BookLoanSeeder bookLoanSeeder;

    protected DevelopmentSeeder(UserSeeder userSeeder, AuthorsAndBooksSeeder authorsAndBooksSeeder, BookCopySeeder bookCopySeeder, BookLoanSeeder bookLoanSeeder) {
        this.userSeeder = userSeeder;
        this.authorsAndBooksSeeder = authorsAndBooksSeeder;
        this.bookCopySeeder = bookCopySeeder;
        this.bookLoanSeeder = bookLoanSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        userSeeder.seed();
        authorsAndBooksSeeder.seed();
        bookCopySeeder.seed();
        bookLoanSeeder.seed();
    }

}