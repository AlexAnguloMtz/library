package com.unison.practicas.desarrollo.core.configuration.seeder;

import com.unison.practicas.desarrollo.core.model.BookCopy;
import com.unison.practicas.desarrollo.core.model.BookLoan;
import com.unison.practicas.desarrollo.core.model.User;
import com.unison.practicas.desarrollo.core.repository.BookCopyRepository;
import com.unison.practicas.desarrollo.core.repository.BookLoanRepository;
import com.unison.practicas.desarrollo.core.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
@Profile({"dev", "test"})
public class BookLoanSeeder {

    private final UserRepository userRepository;
    private final BookCopyRepository bookCopyRepository;
    private final BookLoanRepository bookLoanRepository;

    public BookLoanSeeder(UserRepository userRepository,
                          BookCopyRepository bookCopyRepository,
                          BookLoanRepository bookLoanRepository) {
        this.userRepository = userRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    public void seed() {
        if (bookLoanRepository.count() > 0) {
            return;
        }

        var users = userRepository.findAll();
        var bookCopies = new ArrayList<>(bookCopyRepository.findAll());
        Collections.shuffle(bookCopies);

        var loans = users.stream()
                .flatMap(user -> randomUserLoans(user, bookCopies))
                .toList();

        bookLoanRepository.saveAll(loans);
    }

    private Stream<BookLoan> randomUserLoans(User user, List<BookCopy> availableCopies) {
        if (availableCopies.isEmpty()) return Stream.empty();

        var loanCount = Math.min(ThreadLocalRandom.current().nextInt(0, 6), availableCopies.size());

        return IntStream.range(0, loanCount)
                .mapToObj(i -> {
                    var copy = availableCopies.removeFirst();
                    return createBookLoan(user, copy);
                });
    }

    private BookLoan createBookLoan(User user, BookCopy copy) {
        var now = Instant.now();

        var loan = new BookLoan();
        loan.setUser(user);
        loan.setCopyId(copy.getId());

        var loanDate = now.minus(ThreadLocalRandom.current().nextInt(0, 31), ChronoUnit.DAYS);
        var dueDate = loanDate.plus(14, ChronoUnit.DAYS);

        loan.setLoanDate(loanDate);
        loan.setDueDate(dueDate);

        if (ThreadLocalRandom.current().nextBoolean()) {
            var returnDate = loanDate.plus(ThreadLocalRandom.current().nextInt(1, 15), ChronoUnit.DAYS);
            loan.setReturnDate(returnDate.isAfter(now) ? now : returnDate);
        }

        return loan;
    }
}
