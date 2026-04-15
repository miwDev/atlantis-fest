package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    private String descripcion;
    private String tipo;
    private Double latitud;
    private Double longitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id")
    private Festival festival;

    @Builder.Default
    @OneToMany(mappedBy = "zone")
    private List<Concert> concerts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "zone")
    private List<Foodtruck> foodtrucks = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "zone")
    private List<Shift> shifts = new ArrayList<>();
}