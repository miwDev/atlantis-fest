package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import com.msd.atlantis_fest.entity.Festival;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FestivalMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "zones", ignore = true)
    @Mapping(target = "ticketTypes", ignore = true)
    Festival toEntity(FestivalInputDTO inputDTO);

    FestivalOutputDTO toOutputDTO(Festival entity);
}
