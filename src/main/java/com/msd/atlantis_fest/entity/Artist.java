package com.msd.atlantis_fest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Artist extends User {

    @Column(nullable = false, length = 100)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String surname;

    @Column(unique = true, nullable = false, length = 100)
    private String artistName;

    @Lob
    private String biography;

    @Builder.Default
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Concert> concerts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialMedia> socialMediaLinks = new ArrayList<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "artist_genre",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();
}
