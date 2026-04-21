package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.entity.Artist;
import com.msd.atlantis_fest.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "socialMediaLinks", ignore = true)
    @Mapping(source = "genreIds", target = "genres")
    Artist toEntity(ArtistInputDTO inputDTO);

    @Mapping(source = "genres", target = "genres")
    ArtistOutputDTO toOutputDTO(Artist entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "socialMediaLinks", ignore = true)
    @Mapping(source = "genreIds", target = "genres")
    void updateFromDTO(ArtistInputDTO inputDTO, @MappingTarget Artist entity);

    default List<Genre> map(List<Long> genreIds) {
        if (genreIds == null) {
            return null;
        }
        return genreIds.stream()
                .map(id -> {
                    Genre genre = new Genre();
                    genre.setId(id);
                    return genre;
                })
                .collect(Collectors.toList());
    }

    default List<String> mapGenres(List<Genre> genres) {
        if (genres == null) {
            return null;
        }
        return genres.stream()
                .map(Genre::getNombre)
                .collect(Collectors.toList());
    }
}
