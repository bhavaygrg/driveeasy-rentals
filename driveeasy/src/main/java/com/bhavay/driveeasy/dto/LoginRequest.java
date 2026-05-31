package com.bhavay.driveeasy.dto;

public record LoginRequest(
        String email,
        String password
) {
}