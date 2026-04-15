package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    private String estado;

    @Column(name = "referencia_externa")
    private String referenciaExterna;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Invoice invoice;
}