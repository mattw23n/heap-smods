package com.smods.backend.service;

import com.smods.backend.dto.LoginRequest;
import com.smods.backend.dto.UserDTO;
import com.smods.backend.exception.InvalidCharacterException;
import com.smods.backend.exception.UsernameAlreadyExistsException;
import com.smods.backend.model.User;
import com.smods.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Pattern NON_ENGLISH_PATTERN = Pattern.compile("[^\\p{ASCII}]");

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register a new user
    public User registerUser(UserDTO userDTO) {
        validateInput(userDTO);
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    private void validateInput(UserDTO userDTO) {
        if (NON_ENGLISH_PATTERN.matcher(userDTO.getUsername()).find()) {
            throw new InvalidCharacterException("Username contains invalid characters.");
        }

        if (NON_ENGLISH_PATTERN.matcher(userDTO.getUsername()).find()) {
            throw new InvalidCharacterException("Username contains invalid characters.");
        }

        // Check if username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }

        // You can add more validation rules here as necessary
    }

    // Find a user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Login a user
    public boolean loginUser(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        }
        return false;
    }

    // Update user details
    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            if (!userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Find user by ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
