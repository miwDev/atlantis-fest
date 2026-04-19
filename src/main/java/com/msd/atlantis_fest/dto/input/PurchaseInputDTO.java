package com.msd.atlantis_fest.dto.input;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class PurchaseInputDTO {
    @NotNull
    private Long clientId;
    @NotNull private Long ticketTypeId;
    private Double descuentoAplicado;
}
