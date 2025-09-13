package com.unison.practicas.desarrollo.core.dto;

import java.time.LocalDate;
import java.util.Set;

public record UserPreviewsQuery(
        String search,
        Set<String> role,
        LocalDate memberSinceMin,
        LocalDate memberSinceMax,
        Integer activeBookLoansMin,
        Integer activeBookLoansMax
) {
}