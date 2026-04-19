package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FestivalOutputDTO {
    private Long id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String ubicacionGeneral;
    private String logoUrl;
}
