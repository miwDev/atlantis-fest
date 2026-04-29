package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ArtistInputDTO {
    @NotBlank(message = "El email no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 50, message = "El nombre de usuario no puede tener más de 50 caracteres")
    private String username;

    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    private String surname;

    @NotBlank(message = "El nombre artístico no puede estar vacío")
    @Size(max = 100, message = "El nombre artístico no puede tener más de 100 caracteres")
    private String artistName;

    private String biography;

    private List<Long> genreIds;
}
