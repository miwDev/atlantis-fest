package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShiftOutputDTO {
    private Long id;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private String descripcionTarea;
    private String staffUsername;
    private String zoneNombre;
}
