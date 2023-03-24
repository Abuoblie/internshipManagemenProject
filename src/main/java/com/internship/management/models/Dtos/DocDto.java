package com.internship.management.models.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocDto {
    @NotNull
    private Long id;
    @NotNull
    private Blob doc;

    @NotNull
    private String type;
}
