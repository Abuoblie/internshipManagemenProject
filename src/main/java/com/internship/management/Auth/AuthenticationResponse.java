package com.internship.management.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//this class stores the token
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class AuthenticationResponse {
    private  String token;
}
