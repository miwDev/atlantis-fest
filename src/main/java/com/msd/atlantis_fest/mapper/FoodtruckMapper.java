package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.entity.Foodtruck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FoodtruckMapper {
    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "estaAbierto", ignore = true)
    @Mapping(target = "latitudActual", ignore = true)
    @Mapping(target = "longitudActual", ignore = true)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    Foodtruck toEntity(FoodtruckInputDTO inputDTO);

    @Mapping(source = "zone.nombre", target = "zoneNombre")
    FoodtruckOutputDTO toOutputDTO(Foodtruck entity);

    @Mapping(source = "zoneId", target = "zone.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "estaAbierto", ignore = true)
    @Mapping(target = "latitudActual", ignore = true)
    @Mapping(target = "longitudActual", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    void updateFromDTO(FoodtruckInputDTO inputDTO, @MappingTarget Foodtruck entity);
}