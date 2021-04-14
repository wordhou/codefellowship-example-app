package com.edhou.codefellowship.services;

import com.edhou.codefellowship.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
    ApplicationUser getByUsername(String username);

    boolean existsByUsername(String username);
}
