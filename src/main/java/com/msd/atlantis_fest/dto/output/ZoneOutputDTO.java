package com.msd.atlantis_fest.dto.output;

import com.msd.atlantis_fest.enums.ZoneEnum;
import lombok.Data;

@Data
public class ZoneOutputDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private ZoneEnum tipo;
    private Double latitud;
    private Double longitud;
    private String festivalNombre;
}