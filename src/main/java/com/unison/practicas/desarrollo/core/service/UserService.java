package com.unison.practicas.desarrollo.core.service;

import com.unison.practicas.desarrollo.core.dto.UserPreview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserService {

    private final DateFormat dateFormat;

    public UserService() {
        this.dateFormat = createDateFormat();
    }

    private DateFormat createDateFormat() {
        return new SimpleDateFormat(
                "dd/MMM/yyyy",
                new Locale.Builder().setLanguage("es").setRegion("MX").build()
        );
    }

    public Iterable<UserPreview> getUsersPreviews() {
        return List.of(
                new UserPreview(
                    "23490",
                   "Miguel Angel",
                   "miguel@sample.com",
                   "+52 234 142 4325",
                   "Admin",
                        dateFormat.format(Date.from(Instant.parse("2024-10-10T00:00:00Z"))),
        "10"
                ),
                new UserPreview(
                   "4238",
                   "Daniela Aguirre",
                   "daniela@sample.com",
                   "+52 953 203 4902",
                   "Usuario",
                        dateFormat.format(Date.from(Instant.parse("2025-05-04T00:00:00Z"))),
                   "4"
                ),
                new UserPreview(
                   "23487",
                   "Luisa Armendariz",
                   "luisa@sample.com",
                   "+52 492 593 2912",
                   "Personal",
                        dateFormat.format(Date.from(Instant.parse("2025-11-19T00:00:00Z"))),
                   "19"
                )
        );
    }

}
