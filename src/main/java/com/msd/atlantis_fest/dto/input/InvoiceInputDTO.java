package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceInputDTO {
    @NotNull
    private Long paymentId;
    @NotBlank
    private String datosFiscales;
}
