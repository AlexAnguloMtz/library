package com.unison.practicas.desarrollo.core.dto;

public record RoleResponse(
        String id,
        String name,
        String slug,
        String createdAt,
        String updatedAt
) {
}