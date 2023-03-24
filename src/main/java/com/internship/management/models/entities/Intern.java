package com.internship.management.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Intern extends User implements Serializable {
    @Builder
    public Intern(Long id, @Email String email, String password, Role role, Boolean enable, String firstName, String lastName) {
        super(id, email, password, role, enable);
        this.firstName = firstName;
        this.lastName = lastName;

    }


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Internship> internships = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Document>documents =new ArrayList<>();
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;



}
