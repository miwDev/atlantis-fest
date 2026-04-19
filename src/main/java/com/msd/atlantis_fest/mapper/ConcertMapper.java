package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.entity.Concert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConcertMapper {
    Concert toEntity(ConcertInputDTO inputDTO);
    ConcertOutputDto toOutputDTO(Concert entity);
}
