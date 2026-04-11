package com.msd.atlantis_fest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends User {


    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String surname;

    @Column(unique = true, nullable = false)
    private String artistName;


    private String biografy;
}
