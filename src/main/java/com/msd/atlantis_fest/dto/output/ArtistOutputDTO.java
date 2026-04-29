package com.msd.atlantis_fest.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class ArtistOutputDTO {
    private Long id;
    private String email;
    private String username;
    private String name;
    private String surname;
    private String artistName;
    private String biography;
    private String fotoUrl;
    private List<String> genres;
}
