package com.internship.management.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String streetName;
    @Column(nullable = false)
    private String houseNumber;
    @Column(nullable = false)
    private String zipcode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;


}
