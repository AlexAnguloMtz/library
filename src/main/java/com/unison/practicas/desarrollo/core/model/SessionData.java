package com.unison.practicas.desarrollo.core.model;

import java.time.Instant;

public record SessionData(
        Integer userId,
        String userData,
        Long maxInactiveInterval,
        Instant lastActivity,
        Instant expiresAt
) {
}
