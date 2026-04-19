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

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank
    private String nombre;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "ubicacion_general", length = 200)
    private String ubicacionGeneral;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Builder.Default
    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zone> zones = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketType> ticketTypes = new ArrayList<>();
}
