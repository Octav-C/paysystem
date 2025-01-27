package com.appsdev.PaymentSystem.security.repository;

import com.appsdev.PaymentSystem.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}