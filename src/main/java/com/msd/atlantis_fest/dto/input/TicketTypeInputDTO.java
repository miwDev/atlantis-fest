package com.msd.atlantis_fest.dto.input;

import com.msd.atlantis_fest.enums.TicketEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TicketTypeInputDTO {

    @NotNull(message = "El tipo no puede estar vacío")
    private TicketEnum tipo;

    @NotNull(message = "El precio base no puede ser nulo")
    @Min(value = 0, message = "El precio base no puede ser negativo")
    private Double precioBase;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;

    @NotNull(message = "El máximo disponible no puede ser nulo")
    @Min(value = 0, message = "El máximo disponible no puede ser negativo")
    private Integer maxDisponible;

    @NotNull(message = "El ID del festival no puede ser nulo")
    private Long festivalId;
}
