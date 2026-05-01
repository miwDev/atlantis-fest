package com.msd.atlantis_fest.dto.output;

import lombok.Data;

@Data
public class FoodtruckOutputDTO {
    private Long id;
    private String email;
    private String username;
    private String nombre;
    private String tipoComida;
    private String imagenPortadaUrl;
    private Boolean estaAbierto;
    private Double latitudActual;
    private Double longitudActual;
    private String zoneNombre;
}
