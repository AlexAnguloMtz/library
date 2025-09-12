package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
