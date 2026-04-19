package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import com.msd.atlantis_fest.entity.Shift;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toEntity(ShiftInputDTO inputDTO);
    ShiftOutputDTO toOutputDTO(Shift entity);
}
