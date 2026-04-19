package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "foodtruck")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class Foodtruck extends User {

    @Column(nullable = false, length = 100)
    @NotBlank(message = "El nombre del foodtruck es obligatorio")
    private String nombre;

    @Column(name = "menu_pdf_url", length = 255)
    private String menuPdfUrl;

    @Column(name = "tipo_comida", length = 100)
    @NotBlank(message = "El tipo de comida es obligatorio")
    private String tipoComida;

    @Column(name = "imagen_portada_url", length = 255)
    private String imagenPortadaUrl;

    @Column(name = "esta_abierto", nullable = false)
    @Builder.Default
    private Boolean estaAbierto = false;

    @Column(name = "latitud_actual")
    private Double latitudActual;

    @Column(name = "longitud_actual")
    private Double longitudActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;
}
