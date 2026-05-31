package com.bhavay.driveeasy.config;

import com.bhavay.driveeasy.entity.Car;
import com.bhavay.driveeasy.entity.Customer;
import com.bhavay.driveeasy.enums.CarCategory;
import com.bhavay.driveeasy.enums.Role;
import com.bhavay.driveeasy.repository.CarRepository;
import com.bhavay.driveeasy.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {

            if (carRepository.count() == 0) {

                carRepository.save(
                        Car.builder()
                                .registrationNumber("DL01AB1234")
                                .brand("Toyota")
                                .model("Innova")
                                .year(2024)
                                .dailyRate(BigDecimal.valueOf(2500))
                                .category(CarCategory.SUV)
                                .available(true)
                                .build()
                );
            }

            if (customerRepository.count() == 0) {

                customerRepository.save(
                        Customer.builder()
                                .firstName("Bhavay")
                                .lastName("Garg")
                                .email("bhavay@test.com")
                                .phone("9999999999")
                                .password("test123")
                                .role(Role.CUSTOMER)
                                .build()
                );
            }
        };
    }
}