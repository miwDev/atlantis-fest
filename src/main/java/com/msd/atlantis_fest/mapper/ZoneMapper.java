package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
    Zone toEntity(ZoneInputDTO inputDTO);
    ZoneOutputDTO toOutputDTO(Zone entity);
}
