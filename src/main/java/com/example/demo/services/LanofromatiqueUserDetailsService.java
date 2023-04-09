package com.example.demo.services;

import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LanofromatiqueUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String k) throws UsernameNotFoundException {


        UserModel user = userService.findByEmail(k);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + k);
        }
        // Create a UserDetails object from the user details and roles
        UserDetails userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().split(","))
                .disabled(user.isDisabled())
                .build();
        return userDetails;
    }
}
