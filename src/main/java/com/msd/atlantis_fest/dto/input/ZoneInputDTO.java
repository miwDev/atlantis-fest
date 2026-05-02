package com.msd.atlantis_fest.dto.input;

import com.msd.atlantis_fest.enums.ZoneEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ZoneInputDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

    @NotNull(message = "El tipo de zona es obligatorio")
    private ZoneEnum tipo;

    private Double latitud;

    private Double longitud;

    @NotNull(message = "El ID del festival no puede ser nulo")
    private Long festivalId;
}