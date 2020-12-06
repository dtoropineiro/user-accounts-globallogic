package com.dario.useraccounts.repository;

import com.dario.useraccounts.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT t FROM User t WHERE t.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT t FROM User t WHERE t.email = ?1")
    Optional<User> findByEmail(String email);
}
