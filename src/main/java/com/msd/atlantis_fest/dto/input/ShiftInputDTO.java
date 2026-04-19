package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShiftInputDTO {
    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalDateTime horaInicio;

    @NotNull(message = "La hora de fin no puede ser nula")
    @Future(message = "La hora de fin debe ser en el futuro")
    private LocalDateTime horaFin;

    @Size(max = 500, message = "La descripción de la tarea no puede tener más de 500 caracteres")
    private String descripcionTarea;

    @NotNull(message = "El ID del staff no puede ser nulo")
    private Long staffId;

    @NotNull(message = "El ID de la zona no puede ser nulo")
    private Long zoneId;
}
