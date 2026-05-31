package com.bhavay.driveeasy.repository;

import com.bhavay.driveeasy.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}