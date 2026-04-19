package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShiftInputDTO {
    @NotNull private LocalDateTime horaInicio;
    @NotNull private LocalDateTime horaFin;
    private String descripcionTarea;
    @NotNull private Long staffId;
    @NotNull
    private Long zoneId;
}
