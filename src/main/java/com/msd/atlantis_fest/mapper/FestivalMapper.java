package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import com.msd.atlantis_fest.entity.Festival;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FestivalMapper {
    Festival toEntity(FestivalInputDTO inputDTO);

    FestivalOutputDTO toOutputDTO(Festival entity);
}
