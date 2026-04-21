package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseInputDTO {
    @NotNull(message = "El ID del cliente no puede ser nulo")
    private Long clientId;

    @NotNull(message = "El ID del tipo de ticket no puede ser nulo")
    private Long ticketTypeId;

    private Double descuentoAplicado;
}
