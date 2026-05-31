package com.bhavay.driveeasy.service;

import com.bhavay.driveeasy.dto.CreateCustomerRequest;
import com.bhavay.driveeasy.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(
            CreateCustomerRequest request
    );

    CustomerResponse getCustomer(Long id);

    List<CustomerResponse> getAllCustomers();
}