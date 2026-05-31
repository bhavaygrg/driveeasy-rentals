package com.bhavay.driveeasy.repository;

import com.bhavay.driveeasy.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
            SELECT COUNT(r) > 0
            FROM Reservation r
            WHERE r.car.id = :carId
            AND r.status <> com.bhavay.driveeasy.enums.ReservationStatus.CANCELLED
            AND :startDate < r.endDate
            AND :endDate > r.startDate
            """)
    boolean existsOverlappingReservation(
            Long carId,
            java.time.LocalDate startDate,
            java.time.LocalDate endDate
    );
}