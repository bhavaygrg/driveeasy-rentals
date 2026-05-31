package com.bhavay.driveeasy.security;

import com.bhavay.driveeasy.entity.Customer;
import com.bhavay.driveeasy.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        Customer customer =
                customerRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found"));

        return new User(
                customer.getEmail(),
                customer.getPassword(),
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" +
                                        customer.getRole().name()
                        )
                )
        );
    }
}