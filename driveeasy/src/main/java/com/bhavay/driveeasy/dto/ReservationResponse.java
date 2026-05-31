package com.bhavay.driveeasy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationResponse(

        Long reservationId,
        Long customerId,
        Long carId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal totalAmount,
        String status

) {
}