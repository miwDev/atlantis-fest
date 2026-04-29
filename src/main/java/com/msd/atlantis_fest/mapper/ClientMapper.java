package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.entity.Client;
import com.msd.atlantis_fest.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(source = "favoriteGenreIds", target = "favoriteGenres")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    Client toEntity(ClientInputDTO inputDTO);

    @Mapping(source = "favoriteGenres", target = "favoriteGenres")
    ClientOutputDTO toOutputDTO(Client entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "purchases", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "favoriteGenreIds", target = "favoriteGenres")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    void updateFromDTO(ClientInputDTO inputDTO, @MappingTarget Client entity);

    default List<Genre> map(List<Long> genreIds) {
        if (genreIds == null) return null;
        return genreIds.stream().map(id -> {
            Genre genre = new Genre();
            genre.setId(id);
            return genre;
        }).collect(Collectors.toList());
    }

    default List<String> mapGenres(List<Genre> genres) {
        if (genres == null) return null;
        return genres.stream().map(Genre::getNombre).collect(Collectors.toList());
    }
}