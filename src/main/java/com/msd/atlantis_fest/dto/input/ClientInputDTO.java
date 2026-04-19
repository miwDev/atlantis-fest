package com.msd.atlantis_fest.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientInputDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nombre;
    private String dni;
    private LocalDate fechaNacimiento;
    private List<Long> favoriteGenreIds;
}
