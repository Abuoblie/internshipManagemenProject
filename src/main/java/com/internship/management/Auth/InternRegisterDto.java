package com.internship.management.Auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternRegisterDto {
    private String email;
    private String password;

    private String firstName;
    private String lastName;

    private String role;

    private Blob document;
}
