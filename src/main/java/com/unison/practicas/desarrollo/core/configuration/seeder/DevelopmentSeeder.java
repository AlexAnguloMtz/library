package com.unison.practicas.desarrollo.core.configuration.seeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev"})
public class DevelopmentSeeder implements CommandLineRunner {

    private final UserSeeder userSeeder;
    private final AuthorsAndBooksSeeder authorsAndBooksSeeder;

    protected DevelopmentSeeder(UserSeeder userSeeder, AuthorsAndBooksSeeder authorsAndBooksSeeder) {
        this.userSeeder = userSeeder;
        this.authorsAndBooksSeeder = authorsAndBooksSeeder;
    }

    @Override
    public void run(String... args) throws Exception {
        userSeeder.seed();
        authorsAndBooksSeeder.seed();
    }

}