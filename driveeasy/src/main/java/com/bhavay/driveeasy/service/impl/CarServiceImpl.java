package com.bhavay.driveeasy.service.impl;

import com.bhavay.driveeasy.dto.CarResponse;
import com.bhavay.driveeasy.dto.CreateCarRequest;
import com.bhavay.driveeasy.entity.Car;
import com.bhavay.driveeasy.exception.ResourceNotFoundException;
import com.bhavay.driveeasy.repository.CarRepository;
import com.bhavay.driveeasy.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public CarResponse createCar(CreateCarRequest request) {

        Car car = Car.builder()
                .brand(request.brand())
                .model(request.model())
                .registrationNumber(request.registrationNumber())
                .year(request.year())
                .dailyRate(request.dailyRate())
                .category(request.category())
                .available(true)
                .build();

        Car savedCar = carRepository.save(car);

        return new CarResponse(
                savedCar.getId(),
                savedCar.getBrand(),
                savedCar.getModel(),
                savedCar.getRegistrationNumber(),
                savedCar.getDailyRate()
        );
    }

    @Override
    public CarResponse getCar(Long id) {

        Car car = carRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car not found"));

        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getRegistrationNumber(),
                car.getDailyRate()
        );
    }

    @Override
    public List<CarResponse> getAllCars() {

        return carRepository.findAll()
                .stream()
                .map(car -> new CarResponse(
                        car.getId(),
                        car.getBrand(),
                        car.getModel(),
                        car.getRegistrationNumber(),
                        car.getDailyRate()
                ))
                .toList();
    }
}