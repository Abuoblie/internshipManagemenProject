package com.internship.management.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "_role")
@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String role;



}
