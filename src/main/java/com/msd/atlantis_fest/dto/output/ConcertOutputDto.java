package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConcertOutputDto {
    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String artistName;
    private String zoneName;
}