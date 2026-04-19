package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ZoneInputDTO {
    @NotBlank
    private String nombre;
    private String descripcion;
    private String tipo;
    private Double latitud;
    private Double longitud;
    @NotNull
    private Long festivalId;
}
