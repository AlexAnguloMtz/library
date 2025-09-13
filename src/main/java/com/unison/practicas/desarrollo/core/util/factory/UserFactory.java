package com.unison.practicas.desarrollo.core.util.factory;

import com.github.javafaker.Faker;
import com.unison.practicas.desarrollo.core.model.Role;
import com.unison.practicas.desarrollo.core.model.User;
import com.unison.practicas.desarrollo.core.repository.RoleRepository;
import com.unison.practicas.desarrollo.core.util.CollectionHelpers;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@Profile({"dev", "test"})
public class UserFactory {

    private final Faker faker;
    private final RoleRepository roleRepository;

    public UserFactory(Faker faker, RoleRepository roleRepository) {
        this.faker = faker;
        this.roleRepository = roleRepository;
    }

    public Collection<User> createUsers(int count) {
        if (count < 0) {
            throw new RuntimeException("Count must be greater than 0, got %d".formatted(count));
        }

        List<Role> roles = roleRepository.findAll();

        return IntStream.range(1, count + 1)
                .mapToObj(i -> createUser(i, CollectionHelpers.randomItem(roles)))
                .toList();
    }

    private User createUser(int seed, Role role) {
        var timeStart = Instant.parse("1990-01-01T00:00:00Z");
        var timeEnd = Instant.parse("2025-01-01T00:00:00Z");

        var time = TimeUtils.randomInstantBetween(timeStart, timeEnd);

        var user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setPhoneNumber(faker.phoneNumber().cellPhone());
        user.setEmail(seed + "_" + faker.internet().emailAddress());
        user.setPasswordHash(faker.internet().password(8, 16));
        user.setRoles(Set.of(role));
        user.setCreatedAt(time);
        user.setUpdatedAt(time);

        return user;
    }

}