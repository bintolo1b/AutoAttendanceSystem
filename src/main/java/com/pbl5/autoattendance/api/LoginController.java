package com.pbl5.autoattendance.api;

import com.pbl5.autoattendance.model.User;
import com.pbl5.autoattendance.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // Find user by username
        User user = userRepository.findById(loginRequest.getUsername())
                .orElse(null);

        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user);
        }

        // Return unauthorized if login fails
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
