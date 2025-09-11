package com.unison.practicas.desarrollo.core.dto;

public record UserPreview(
     String id,
     String name,
     String email,
     String phone,
     String role,
     String memberSince,
     String activeLoans
) {
}