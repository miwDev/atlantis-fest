package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Client extends User {

    @NotBlank
    private String nombre;

    @Column(unique = true)
    private String dni;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private List<Purchase> purchases = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "client")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_genre",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> favoriteGenres = new ArrayList<>();
}