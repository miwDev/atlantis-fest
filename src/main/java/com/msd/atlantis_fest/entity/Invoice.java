package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_factura", unique = true)
    private String numeroFactura;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "datos_fiscales")
    private String datosFiscales;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}