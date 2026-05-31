package com.bhavay.driveeasy.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateReservationRequest(

        @NotNull
        Long customerId,

        @NotNull
        Long carId,

        @NotNull
        @Future
        LocalDate startDate,

        @NotNull
        @Future
        LocalDate endDate

) {
}