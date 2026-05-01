package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientOutputDTO {
    private Long id;
    private String email;
    private String username;
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private List<String> favoriteGenres;
}
