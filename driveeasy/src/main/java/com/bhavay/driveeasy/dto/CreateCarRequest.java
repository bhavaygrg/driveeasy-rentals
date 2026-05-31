package com.bhavay.driveeasy.dto;

import com.bhavay.driveeasy.enums.CarCategory;

import java.math.BigDecimal;

public record CreateCarRequest(

        String brand,
        String model,
        String registrationNumber,
        Integer year,
        BigDecimal dailyRate,
        CarCategory category

) {
}