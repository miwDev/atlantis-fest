package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class SocialMediaInputDTO {
    @NotBlank(message = "El tipo no puede estar vacío")
    @Size(max = 50, message = "El tipo no puede tener más de 50 caracteres")
    private String tipo;

    @NotBlank(message = "La URL no puede estar vacía")
    @URL(message = "La URL debe ser válida")
    @Size(max = 255, message = "La URL no puede tener más de 255 caracteres")
    private String url;

    @NotNull(message = "El ID del artista no puede ser nulo")
    private Long artistId;
}
