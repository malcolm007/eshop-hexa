package com.global.eshophexa.models;

import java.time.LocalDate;

public record PersonDomain(

        String lastName,
        String firstName,
        LocalDate birthDate,
        String role,
        String email,
        String password
) {
}
