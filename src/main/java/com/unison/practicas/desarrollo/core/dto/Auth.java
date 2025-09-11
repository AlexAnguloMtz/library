package com.unison.practicas.desarrollo.core.dto;

public record Auth(
        String userId,
        String email,
        String role
) {
}