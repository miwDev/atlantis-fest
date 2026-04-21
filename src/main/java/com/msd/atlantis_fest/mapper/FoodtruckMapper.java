package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.entity.Foodtruck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodtruckMapper {
    @Mapping(source = "zoneId", target = "zone.id")
    Foodtruck toEntity(FoodtruckInputDTO inputDTO);

    @Mapping(source = "zone.nombre", target = "zoneNombre")
    FoodtruckOutputDTO toOutputDTO(Foodtruck entity);
}
