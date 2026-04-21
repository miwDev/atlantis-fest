package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import com.msd.atlantis_fest.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    @Mapping(source = "staffId", target = "staff.id")
    @Mapping(source = "zoneId", target = "zone.id")
    Shift toEntity(ShiftInputDTO inputDTO);

    @Mapping(source = "staff.username", target = "staffUsername")
    @Mapping(source = "zone.nombre", target = "zoneNombre")
    ShiftOutputDTO toOutputDTO(Shift entity);
}
