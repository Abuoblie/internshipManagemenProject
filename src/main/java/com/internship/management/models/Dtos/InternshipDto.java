package com.internship.management.models.Dtos;

import com.internship.management.models.entities.Enterprise;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InternshipDto {
    private Long id;
    @NotNull
    private String category;

    @NotNull
    private String jobTitle;

    @NotNull
    private String jobDescription;

    @NotNull
    private LocalDate expiringDate;

    @NotNull
    private  String reference;

    private Enterprise enterprise;

}
