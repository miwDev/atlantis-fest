package com.msd.atlantis_fest.dto.output;

import com.msd.atlantis_fest.enums.TicketEnum;
import lombok.Data;

@Data
public class TicketTypeOutputDTO {
    private Long id;
    private TicketEnum tipo;
    private Double precioBase;
    private String descripcion;
    private Integer maxDisponible;
    private String festivalNombre;
}
