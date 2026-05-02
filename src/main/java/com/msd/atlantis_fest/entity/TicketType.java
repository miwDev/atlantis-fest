package com.msd.atlantis_fest.entity;

import com.msd.atlantis_fest.enums.TicketEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "ticket_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TicketEnum tipo;

    @Column(name = "precio_base", nullable = false)
    private Double precioBase;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "max_disponible", nullable = false)
    private Integer maxDisponible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;
}
