package com.bhavay.driveeasy.controller;

import com.bhavay.driveeasy.dto.CarResponse;
import com.bhavay.driveeasy.dto.CreateCarRequest;
import com.bhavay.driveeasy.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarResponse createCar(
            @RequestBody CreateCarRequest request
    ) {
        return carService.createCar(request);
    }

    @GetMapping("/{id}")
    public CarResponse getCar(
            @PathVariable Long id
    ) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }
}