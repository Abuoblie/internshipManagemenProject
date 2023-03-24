package com.internship.management.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"intern"})
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Intern intern;

    @Lob
    @Column(nullable = false)
    private Blob docs;

    @Column(nullable = false)
    private String type;

}
