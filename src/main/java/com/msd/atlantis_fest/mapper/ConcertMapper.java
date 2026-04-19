package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.entity.Concert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConcertMapper {
    @Mapping(source = "artistId", target = "artist.id")
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(target = "id", ignore = true)
    Concert toEntity(ConcertInputDTO inputDTO);

    @Mapping(source = "artist.artistName", target = "artistName")
    @Mapping(source = "zone.nombre", target = "zoneName")
    ConcertOutputDto toOutputDTO(Concert entity);
}
