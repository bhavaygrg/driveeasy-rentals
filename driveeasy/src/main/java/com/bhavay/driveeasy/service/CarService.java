package com.bhavay.driveeasy.service;

import com.bhavay.driveeasy.dto.CarResponse;
import com.bhavay.driveeasy.dto.CreateCarRequest;

import java.util.List;

public interface CarService {

    CarResponse createCar(CreateCarRequest request);

    CarResponse getCar(Long id);

    List<CarResponse> getAllCars();
}