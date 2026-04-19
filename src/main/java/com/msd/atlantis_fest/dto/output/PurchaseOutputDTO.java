package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseOutputDTO {
    private Long id;
    private LocalDateTime fechaCompra;
    private Double precioFinal;
    private Double descuentoAplicado;
    private String clientUsername;
    private String ticketTipo;
}
