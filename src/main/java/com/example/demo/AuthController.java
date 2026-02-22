package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository repo;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        repo.save(user);
        return "Signup successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return repo.findByEmail(user.getEmail())
                .filter(u -> u.getPassword().equals(user.getPassword()))
                .map(u -> "Login successful!")
                .orElse("Invalid email or password");
    }
}