package com.internship.management.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseDto {
    private Long id;
    private String email;
    private String password;

    private String role;
    private AddressDto address;
    private  String name;
}
