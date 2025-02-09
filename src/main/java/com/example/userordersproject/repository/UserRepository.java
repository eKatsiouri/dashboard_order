package com.example.userordersproject.repository;

import com.example.userordersproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    boolean existsUserByUsername(String username);

    User findByEmail(String email);
}
