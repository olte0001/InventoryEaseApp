package com.group17.inventoryease.ums.services;

/*
* "This service will be responsible for loading user-specific data."
* Source: https://medium.com/@bubu.tripathy/role-based-access-control-with-spring-security-ca59d2ce80b0
* */

import com.group17.inventoryease.ums.repositories.UserRepository;
import com.group17.inventoryease.ums.models.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return user;
    }
}