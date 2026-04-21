package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;
import com.msd.atlantis_fest.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "clients", ignore = true)
    Genre toEntity(GenreInputDTO inputDTO);

    GenreOutputDTO toOutputDTO(Genre entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "clients", ignore = true)
    void updateFromDTO(GenreInputDTO inputDTO, @MappingTarget Genre entity);
}
