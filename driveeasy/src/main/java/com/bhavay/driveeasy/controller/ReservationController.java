package com.bhavay.driveeasy.controller;

import com.bhavay.driveeasy.dto.CreateReservationRequest;
import com.bhavay.driveeasy.dto.ReservationResponse;
import com.bhavay.driveeasy.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ReservationResponse createReservation(
            @Valid @RequestBody CreateReservationRequest request) {

        return reservationService.createReservation(request);
    }
    @GetMapping("/{id}")
    public ReservationResponse getReservation(
            @PathVariable Long id) {

        return reservationService.getReservation(id);
    }
    @GetMapping
    public List<ReservationResponse> getAllReservations() {

        return reservationService.getAllReservations();
    }
}