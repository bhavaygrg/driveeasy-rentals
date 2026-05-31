package com.bhavay.driveeasy.service.impl;

import com.bhavay.driveeasy.dto.LoginRequest;
import com.bhavay.driveeasy.dto.LoginResponse;
import com.bhavay.driveeasy.entity.Customer;
import com.bhavay.driveeasy.exception.ResourceNotFoundException;
import com.bhavay.driveeasy.repository.CustomerRepository;
import com.bhavay.driveeasy.security.JwtService;
import com.bhavay.driveeasy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Customer customer = customerRepository.findByEmail(
                        request.email())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invalid email or password"));

        boolean matches =
                passwordEncoder.matches(
                        request.password(),
                        customer.getPassword());

        if (!matches) {
            throw new ResourceNotFoundException(
                    "Invalid email or password");
        }

        String token =
                jwtService.generateToken(
                        customer.getEmail());

        return new LoginResponse(token);
    }
}