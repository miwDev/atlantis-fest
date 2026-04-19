package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import com.msd.atlantis_fest.entity.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {
    Purchase toEntity(PurchaseInputDTO inputDTO);
    PurchaseOutputDTO toOutputDTO(Purchase entity);
}
