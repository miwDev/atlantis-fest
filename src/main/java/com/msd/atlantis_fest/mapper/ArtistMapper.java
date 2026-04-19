package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.entity.Artist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    Artist toEntity(ArtistInputDTO inputDTO);
    ArtistOutputDTO toOutputDTO(Artist entity);
}
