package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import com.msd.atlantis_fest.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "ticketTypeId", target = "ticketType.id")
    Purchase toEntity(PurchaseInputDTO inputDTO);

    @Mapping(source = "client.username", target = "clientUsername")
    @Mapping(source = "ticketType.tipo", target = "ticketTipo")
    PurchaseOutputDTO toOutputDTO(Purchase entity);
}
