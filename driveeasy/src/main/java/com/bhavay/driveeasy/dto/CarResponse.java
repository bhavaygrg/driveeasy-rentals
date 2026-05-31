package com.bhavay.driveeasy.dto;

import java.math.BigDecimal;

public record CarResponse(

        Long id,
        String brand,
        String model,
        String registrationNumber,
        BigDecimal dailyRate

) {
}