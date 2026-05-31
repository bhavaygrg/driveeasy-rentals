package com.bhavay.driveeasy.service;

import com.bhavay.driveeasy.dto.LoginRequest;
import com.bhavay.driveeasy.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}