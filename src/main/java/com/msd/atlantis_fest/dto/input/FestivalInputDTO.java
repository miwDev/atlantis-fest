package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FestivalInputDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @FutureOrPresent(message = "La fecha de inicio debe ser en el presente o en el futuro")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @FutureOrPresent(message = "La fecha de fin debe ser en el presente o en el futuro")
    private LocalDate fechaFin;

    @Size(max = 200, message = "La ubicación general no puede tener más de 200 caracteres")
    private String ubicacionGeneral;

    @Size(max = 255, message = "La URL del logo no puede tener más de 255 caracteres")
    private String logoUrl;
}
