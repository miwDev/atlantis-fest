package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.entity.TicketType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketTypeMapper {
    TicketType toEntity(TicketTypeInputDTO inputDTO);
    TicketTypeOutputDTO toOutputDTO(TicketType entity);
}
