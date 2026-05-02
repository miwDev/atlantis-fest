package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConcertInputDTO {
    @NotNull(message = "La fecha no puede ser nula")
    @FutureOrPresent(message = "La fecha debe ser en el presente o en el futuro")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin no puede ser nula")
    private LocalTime horaFin;

    @NotNull(message = "El ID del artista no puede ser nulo")
    private Long artistId;

    @NotNull(message = "El ID de la zona no puede ser nulo")
    private Long zoneId;
}