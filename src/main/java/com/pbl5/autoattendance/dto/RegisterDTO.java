package com.pbl5.autoattendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private List<String> roles;
    private String name;
    private String phone;
    private String email;
}



