package com.pbl5.autoattendance.api;
import com.pbl5.autoattendance.dto.RegisterDTO;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.User;
import com.pbl5.autoattendance.model.Authority;
import com.pbl5.autoattendance.embedded.AuthorityId;
import com.pbl5.autoattendance.repository.StudentRepository;
import com.pbl5.autoattendance.repository.UserRepository;
import com.pbl5.autoattendance.repository.AuthorityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final StudentRepository studentRepository;

    public RegisterController(UserRepository userRepository, 
                            AuthorityRepository authorityRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> register(@RequestBody RegisterDTO request) {
        // Check if username already exists
        if (userRepository.existsById(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Create new user
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .enabled(true)
                .build();

        userRepository.save(user);

        // Create authority
        Authority authority = new Authority();
        AuthorityId authorityId = new AuthorityId();
        authorityId.setUsername(user.getUsername());
        authorityId.setAuthority(request.getRole());
        authority.setId(authorityId);
        authority.setUser(user);

        authorityRepository.save(authority);

        System.out.println(authority.getId().getAuthority());

        // Set authority to user's authorities list
        user.setAuthorities(List.of(authority));
        userRepository.save(user);

        // Save user (this will cascade save the authority)


        // Create and save student
        Student student = new Student();
        student.setUser(user);
        student.setName(request.getName());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setEmail(request.getEmail());
        studentRepository.save(student);

        return ResponseEntity.ok("Registration successful");
    }
}
