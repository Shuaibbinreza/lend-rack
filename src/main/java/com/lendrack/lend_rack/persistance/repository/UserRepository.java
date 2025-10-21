package com.lendrack.lend_rack.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lendrack.lend_rack.persistance.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
