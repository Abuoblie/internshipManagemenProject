package com.internship.management.restControllers;

import com.internship.management.Auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/registerIntern")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody InternRegisterDto request)
    {
        return  ResponseEntity.ok(authenticationService.registerIntern(request));
    }
    @PostMapping("/registerEnterprise")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request)
    {
        return  ResponseEntity.ok(authenticationService.registerEnterprise(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
