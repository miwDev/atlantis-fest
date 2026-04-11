package com.msd.atlantis_fest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff extends User {

    @Column(name = "puesto_especifico")
    @NotEmpty(message = "El puesto no puede ser vacío")
    private String duty;
}
