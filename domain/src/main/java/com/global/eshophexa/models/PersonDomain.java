package com.global.eshophexa.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDomain {

    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String role;
    private String email;
    private String password;
}
