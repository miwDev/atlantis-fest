package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.entity.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    @Mapping(source = "festivalId", target = "festival.id")
    @Mapping(target = "id", ignore = true)
    TicketType toEntity(TicketTypeInputDTO inputDTO);

    @Mapping(source = "festival.nombre", target = "festivalNombre")
    TicketTypeOutputDTO toOutputDTO(TicketType entity);

    @Mapping(source = "festivalId", target = "festival.id")
    @Mapping(target = "id", ignore = true)
    void updateFromDTO(TicketTypeInputDTO inputDTO, @MappingTarget TicketType entity);
}
