package com.global.eshophexa.infrastructure.security.services;

import com.global.eshophexa.infrastructure.security.models.JwtAuthenticationResponse;
import com.global.eshophexa.infrastructure.security.models.SignUpRequest;
import com.global.eshophexa.infrastructure.security.models.SigninRequest;

public interface AuthenticationService {
        JwtAuthenticationResponse signup(SignUpRequest request);

        JwtAuthenticationResponse signin(SigninRequest request);
}
