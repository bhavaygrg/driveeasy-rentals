package com.bhavay.driveeasy.service.impl;

import com.bhavay.driveeasy.dto.CreateReservationRequest;
import com.bhavay.driveeasy.dto.ReservationResponse;
import com.bhavay.driveeasy.entity.Car;
import java.util.List;
import com.bhavay.driveeasy.entity.Customer;
import com.bhavay.driveeasy.entity.Reservation;
import com.bhavay.driveeasy.enums.ReservationStatus;
import com.bhavay.driveeasy.exception.ReservationConflictException;
import com.bhavay.driveeasy.exception.ResourceNotFoundException;
import com.bhavay.driveeasy.repository.CarRepository;
import com.bhavay.driveeasy.repository.CustomerRepository;
import com.bhavay.driveeasy.repository.ReservationRepository;
import com.bhavay.driveeasy.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Override
    public ReservationResponse createReservation(CreateReservationRequest request) {

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer not found"));

        Car car = carRepository.findById(request.carId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car not found"));

        boolean conflict =
                reservationRepository.existsOverlappingReservation(
                        car.getId(),
                        request.startDate(),
                        request.endDate()
                );

        if (conflict) {
            throw new ReservationConflictException(
                    "Car already booked for selected dates"
            );
        }

        long days = ChronoUnit.DAYS.between(
                request.startDate(),
                request.endDate()
        );

        BigDecimal totalAmount =
                car.getDailyRate()
                        .multiply(BigDecimal.valueOf(days));

        Reservation reservation = Reservation.builder()
                .customer(customer)
                .car(car)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .totalAmount(totalAmount)
                .status(ReservationStatus.CONFIRMED)
                .build();

        Reservation savedReservation =
                reservationRepository.save(reservation);

        return new ReservationResponse(
                savedReservation.getId(),
                customer.getId(),
                car.getId(),
                savedReservation.getStartDate(),
                savedReservation.getEndDate(),
                savedReservation.getTotalAmount(),
                savedReservation.getStatus().name()
        );
    }
    @Override
    public ReservationResponse getReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation not found"));

        return new ReservationResponse(
                reservation.getId(),
                reservation.getCustomer().getId(),
                reservation.getCar().getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getTotalAmount(),
                reservation.getStatus().name()
        );
    }
    @Override
    public List<ReservationResponse> getAllReservations() {

        return reservationRepository.findAll()
                .stream()
                .map(r -> new ReservationResponse(
                        r.getId(),
                        r.getCustomer().getId(),
                        r.getCar().getId(),
                        r.getStartDate(),
                        r.getEndDate(),
                        r.getTotalAmount(),
                        r.getStatus().name()
                ))
                .toList();
    }
}