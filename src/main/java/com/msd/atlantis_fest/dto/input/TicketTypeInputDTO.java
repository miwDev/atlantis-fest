package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketTypeInputDTO {
    @NotBlank
    private String tipo;
    @NotNull
    private Double precioBase;
    private String descripcion;
    @NotNull
    private Integer maxDisponible;
    @NotNull
    private Long festivalId;
}
