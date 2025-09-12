package com.unison.practicas.desarrollo.core.repository;

import com.unison.practicas.desarrollo.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}