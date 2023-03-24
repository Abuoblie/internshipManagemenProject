package com.internship.management.Auth;


import com.internship.management.models.Dtos.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//register FormDto
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    //Dto enterprise entity
    private String email;
    private String password;

    private String role;
    private AddressDto address;
    private  String name;
}
