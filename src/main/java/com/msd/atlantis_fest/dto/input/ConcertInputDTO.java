package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConcertInputDTO {
    @NotNull
    private LocalDate fecha;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFin;
    private Integer duracionEstimada;
    @NotNull
    private Long artistId;
    @NotNull
    private Long zoneId;
}
