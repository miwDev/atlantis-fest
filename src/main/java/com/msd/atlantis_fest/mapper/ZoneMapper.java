package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    @Mapping(source = "festivalId", target = "festival.id")
    Zone toEntity(ZoneInputDTO inputDTO);

    @Mapping(source = "festival.nombre", target = "festivalNombre")
    ZoneOutputDTO toOutputDTO(Zone entity);
}
