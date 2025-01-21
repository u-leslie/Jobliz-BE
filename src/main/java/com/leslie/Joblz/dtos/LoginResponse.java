package com.leslie.Joblz.dtos;

import com.leslie.Joblz.enums.UserRole;
import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserRole role;

    public LoginResponse(String token, UserRole role) {
        this.token = token;
        this.role = role;
    }
}
