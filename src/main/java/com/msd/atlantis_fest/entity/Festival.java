package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "festival")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "ubicacion_general")
    private String ubicacionGeneral;

    @Column(name = "logo_url")
    private String logoUrl;

    @Builder.Default
    @OneToMany(mappedBy = "festival")
    private List<Zone> zones = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "festival")
    private List<TicketType> ticketTypes = new ArrayList<>();
}