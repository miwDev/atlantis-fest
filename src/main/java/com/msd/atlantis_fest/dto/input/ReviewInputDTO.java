package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewInputDTO {
    @NotBlank(message = "El tipo de objetivo no puede estar vacío")
    @Size(max = 50, message = "El tipo de objetivo no puede tener más de 50 caracteres")
    private String targetType;

    @NotNull(message = "El ID del objetivo no puede ser nulo")
    private Long targetId;

    @NotNull(message = "Las estrellas no pueden ser nulas")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer stars;

    @Size(max = 1000, message = "El comentario no puede tener más de 1000 caracteres")
    private String comment;
}
