package com.global.eshophexa.infrastructure.security.services.impl;

import com.global.eshophexa.infrastructure.databse.entities.PersonEntity;
import com.global.eshophexa.infrastructure.databse.entities.Role;
import com.global.eshophexa.infrastructure.databse.repositories.PersonRepository;
import com.global.eshophexa.infrastructure.security.models.JwtAuthenticationResponse;
import com.global.eshophexa.infrastructure.security.models.SignUpRequest;
import com.global.eshophexa.infrastructure.security.models.SigninRequest;
import com.global.eshophexa.infrastructure.security.services.AuthenticationService;
import com.global.eshophexa.infrastructure.security.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PersonRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = PersonEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
