package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FestivalInputDTO {
    @NotBlank
    private String nombre;
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFin;
    private String ubicacionGeneral;
    private String logoUrl;
}
