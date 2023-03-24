package com.internship.management.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Enterprise extends User implements Serializable {

    public Enterprise(Long id, @Email String email, String password, Role role, Boolean enable, Address address, String name) {
        super(id, email, password, role, enable);
        this.address = address;
        this.name = name;
    }

    public Enterprise(Address address, String name) {
        this.address = address;
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    @NotNull
    @Column(nullable = false,unique = true)
    private  String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;




}
