package com.bhavay.driveeasy.dto;

public record CustomerResponse(

        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String role

) {
}