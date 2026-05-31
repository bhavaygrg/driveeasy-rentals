package com.bhavay.driveeasy.service;

import com.bhavay.driveeasy.dto.CreateReservationRequest;
import com.bhavay.driveeasy.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse createReservation(
            CreateReservationRequest request
    );
    ReservationResponse getReservation(Long reservationId);

    List<ReservationResponse> getAllReservations();

}