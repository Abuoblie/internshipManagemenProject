package com.internship.management.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Internship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Enterprise enterprise;

    @ManyToOne
    private Category category;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.Open;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private LocalDate expiringDate;

    @Column(nullable = false, unique = true)
    private  String reference;






}
