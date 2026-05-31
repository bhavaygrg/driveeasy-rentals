package com.bhavay.driveeasy.dto;

import com.bhavay.driveeasy.enums.Role;

public record CreateCustomerRequest(

        String firstName,
        String lastName,
        String email,
        String phone,
        String password,
        Role role

) {
}