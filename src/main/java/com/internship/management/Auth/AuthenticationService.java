package com.internship.management.Auth;

import com.internship.management.models.Dtos.DocDto;
import com.internship.management.models.entities.*;
import com.internship.management.repositories.*;
import com.internship.management.servicies.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EnterpriseRepository enterpriseRepository;
    private final AddressRepository addressRepository;
    private  final RoleRepository roleRepository;
    private final InternUserRepository internUserRepository;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse registerIntern(InternRegisterDto request){
        Role role1 = roleRepository.findByRole(request.getRole()).orElseThrow();

        Intern internUser = new Intern(null, request.getEmail(), passwordEncoder.encode(request.getPassword()),
                role1,true, request.getFirstName(), request.getLastName() );

        Document doc = Document.builder().docs(request.getDocument()).build();

        internUser.getDocuments().add(doc);

        internUserRepository.save(internUser);
        var jwtToken = jwtService.generateToken(internUser);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
    public AuthenticationResponse registerEnterprise(RegisterRequest request){
        Role role1 = roleRepository.findByRole(request.getRole()).orElseThrow();
        Address address = Address.builder()
                .city(request.getAddress().getCity())
                .houseNumber(request.getAddress().getHouseNumber())
                .streetName(request.getAddress().getStreetName())
                .zipcode(request.getAddress().getZipcode())
                .country(request.getAddress().getCountry())
                .build();


        Enterprise enterprise = new Enterprise(null, request.getEmail(), passwordEncoder.encode(request.getPassword()),
                role1,true, address, request.getName());

        enterpriseRepository.save(enterprise);
        var jwtToken = jwtService.generateToken(enterprise);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
