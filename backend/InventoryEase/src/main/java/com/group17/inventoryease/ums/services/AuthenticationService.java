package com.group17.inventoryease.ums.services;

// All code in this class has been taken from https://medium.com/@tericcabrel/implement-jwt-authentication-in-a-spring-boot-3-application-5839e4fd8fac

import com.group17.inventoryease.dtos.LoginRequest;
import com.group17.inventoryease.models.User;
import com.group17.inventoryease.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> authenticate(LoginRequest input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    input.getUsername(),
                    input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
    }
}