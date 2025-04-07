package com.pbl5.autoattendance.api;
import com.pbl5.autoattendance.dto.RegisterDTO;
import com.pbl5.autoattendance.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterAPI {
    private final UserService userService;

    public RegisterAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        return userService.createNewUser(registerDTO);
    }
}
