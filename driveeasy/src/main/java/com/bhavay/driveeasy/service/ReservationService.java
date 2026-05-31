package com.bhavay.driveeasy.service;

import com.bhavay.driveeasy.dto.CreateReservationRequest;
import com.bhavay.driveeasy.dto.ReservationResponse;

public interface ReservationService {

    ReservationResponse createReservation(
            CreateReservationRequest request
    );

}