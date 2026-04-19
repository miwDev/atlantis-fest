package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank
    private String nombre;

    @Builder.Default
    @ManyToMany(mappedBy = "genres")
    private List<Artist> artists = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "favoriteGenres")
    private List<Client> clients = new ArrayList<>();
}
