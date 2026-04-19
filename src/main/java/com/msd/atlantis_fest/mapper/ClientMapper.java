package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientInputDTO inputDTO);
    ClientOutputDTO toOutputDTO(Client entity);
}
