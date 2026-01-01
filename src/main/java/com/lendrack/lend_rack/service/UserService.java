package com.lendrack.lend_rack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lendrack.lend_rack.persistance.entity.User;
import com.lendrack.lend_rack.persistance.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getOrCreateUser(String name, String email, String picture) {
        Optional<User> optionalUser = findByEmail(email);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPicture(picture);
            return saveUser(user);
        }
    }

    public User registerUser(String name, String email, String password) {
        // Check if user already exists
        if (findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists with email: " + email);
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setProvider("local"); // Indicate this is a local registration

        return saveUser(user);
    }
}