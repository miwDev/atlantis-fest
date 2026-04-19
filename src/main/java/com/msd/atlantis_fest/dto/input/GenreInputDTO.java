package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenreInputDTO {
    @NotBlank
    private String nombre;
}
