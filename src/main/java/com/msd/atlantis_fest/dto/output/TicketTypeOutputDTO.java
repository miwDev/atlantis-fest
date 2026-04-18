package com.msd.atlantis_fest.dto.output;

import lombok.Data;

@Data
public class TicketTypeOutputDTO {
    private Long id;
    private String tipo;
    private Double precioBase;
    private String descripcion;
    private Integer maxDisponible;
    private String festivalNombre;
}
