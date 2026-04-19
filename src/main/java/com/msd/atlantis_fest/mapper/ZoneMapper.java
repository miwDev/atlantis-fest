package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    @Mapping(source = "festivalId", target = "festival.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "foodtrucks", ignore = true)
    @Mapping(target = "shifts", ignore = true)
    Zone toEntity(ZoneInputDTO inputDTO);

    @Mapping(source = "festival.nombre", target = "festivalNombre")
    ZoneOutputDTO toOutputDTO(Zone entity);

    @Mapping(source = "festivalId", target = "festival.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "concerts", ignore = true)
    @Mapping(target = "foodtrucks", ignore = true)
    @Mapping(target = "shifts", ignore = true)
    void updateFromDTO(ZoneInputDTO inputDTO, @MappingTarget Zone entity);
}
