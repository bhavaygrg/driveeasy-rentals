package com.bhavay.driveeasy.controller;

import com.bhavay.driveeasy.dto.LoginRequest;
import com.bhavay.driveeasy.dto.LoginResponse;
import com.bhavay.driveeasy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}