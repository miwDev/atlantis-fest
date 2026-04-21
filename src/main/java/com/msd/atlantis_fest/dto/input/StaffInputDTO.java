package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StaffInputDTO {
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 50, message = "El nombre de usuario no puede tener más de 50 caracteres")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
}
