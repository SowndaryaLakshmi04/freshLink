package com.freshlink.controller;

import com.freshlink.model.User;
import com.freshlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 1. Strict Registration (Fails if account already exists)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getPhone() == null || user.getPhone().length() < 10) {
            return ResponseEntity.badRequest().body(Map.of("error", "Valid 10-digit phone number required"));
        }
        if (userRepository.findByPhone(user.getPhone()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("error", "Phone number already registered! Please sign in instead."));
        }
        User saved = userRepository.save(user);
        System.out.println("✅ New user created in PostgreSQL: " + saved.getName());
        return ResponseEntity.ok(saved);
    }

    // 2. Strict Login (Fails if account does not exist)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String phone = credentials.get("phone");
        Optional<User> userOpt = userRepository.findByPhone(phone);

        if (userOpt.isPresent()) {
            System.out.println("✅ User logged in from PostgreSQL: " + userOpt.get().getName());
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.status(401).body(Map.of("error", "Account not found. Please create an account first."));
    }
}