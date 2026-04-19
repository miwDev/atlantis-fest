package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceOutputDTO {
    private Long id;
    private String numeroFactura;
    private LocalDateTime fechaEmision;
    private String datosFiscales;
    private Long paymentId;
}
