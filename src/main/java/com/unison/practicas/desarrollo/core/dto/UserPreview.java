package com.unison.practicas.desarrollo.core.dto;

import java.util.List;

public record UserPreview(
     String id,
     String name,
     String email,
     String phone,
     List<RoleResponse> roles,
     String memberSince,
     String activeLoans
) {
}