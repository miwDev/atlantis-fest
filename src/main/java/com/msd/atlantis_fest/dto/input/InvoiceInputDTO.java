package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InvoiceInputDTO {
    @NotNull(message = "El ID del pago no puede ser nulo")
    private Long paymentId;

    @NotBlank(message = "Los datos fiscales no pueden estar vacíos")
    @Size(max = 500, message = "Los datos fiscales no pueden tener más de 500 caracteres")
    private String datosFiscales;
}
