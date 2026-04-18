package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodtruckInputDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nombre;
    private String menuPdfUrl;
    @NotBlank
    private String tipoComida;
    private String imagenPortadaUrl;
    private Long zoneId;
}
