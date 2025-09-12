package com.unison.practicas.desarrollo.core.service;

import com.unison.practicas.desarrollo.core.dto.UserPreview;
import com.unison.practicas.desarrollo.core.model.User;
import com.unison.practicas.desarrollo.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DateFormat dateFormat;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.dateFormat = createDateFormat();
    }

    private DateFormat createDateFormat() {
        return new SimpleDateFormat(
                "dd/MMM/yyyy",
                new Locale.Builder().setLanguage("es").setRegion("MX").build()
        );
    }

    public Iterable<UserPreview> getUsersPreviews() {
        Collection<User> users = userRepository.findAll();
        return users.stream()
                .map(this::toUserPreview)
                .toList();
    }

    private UserPreview toUserPreview(User user) {
        return new UserPreview(
                user.getId().toString(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                new ArrayList<>(user.getRoles()).get(0).getName(),
                dateFormat.format(Date.from(user.getCreatedAt())),
                "1"
        );
    }

}