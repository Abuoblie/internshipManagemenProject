package com.internship.management.models.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    @NotNull
    private String streetName;
    @NotNull
    private String houseNumber;
    @NotNull
    private String zipcode;
    @NotNull
    private String city;
    @NotNull
    private String country;
}
