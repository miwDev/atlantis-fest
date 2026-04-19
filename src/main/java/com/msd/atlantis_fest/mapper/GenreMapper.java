package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;
import com.msd.atlantis_fest.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toEntity(GenreInputDTO inputDTO);
    GenreOutputDTO toOutputDTO(Genre entity);
}
