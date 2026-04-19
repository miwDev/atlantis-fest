package com.msd.atlantis_fest.dto.output;

import lombok.Data;

@Data
public class ZoneOutputDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Double latitud;
    private Double longitud;
    private String festivalNombre;
}
