package com.msd.atlantis_fest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodTruck extends User {
    @Column(nullable = false)
    @NotBlank(message = "El nombre del foodtruck es obligatorio")
    private String nombre;

    @Column(name = "menu_pdf_url")
    private String menuPdfUrl;

    @Column(name = "tipo_comida")
    @NotBlank(message = "El tipo de comida es obligatorio")
    private String tipoComida;

    @Column(name = "imagen_portada_url")
    private String imagenPortadaUrl;

    @Column(name = "esta_abierto", nullable = false)
    private Boolean estaAbierto = false; // Por defecto lo ponemos a false (cerrado)

    @Column(name = "latitud_actual")
    private Double latitudActual;

    @Column(name = "longitud_actual")
    private Double longitudActual;

}
