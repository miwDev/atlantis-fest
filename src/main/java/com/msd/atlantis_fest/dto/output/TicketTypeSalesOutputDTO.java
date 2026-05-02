package com.msd.atlantis_fest.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeSalesOutputDTO {
    private Long ticketTypeId;
    private String tipo;
    private Double precioBase;
    private Long vendidos;
    private Long disponibles;
    private Double ingresoTotal;
}