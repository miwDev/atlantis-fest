package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.entity.Foodtruck;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodtruckMapper {
    Foodtruck toEntity(FoodtruckInputDTO inputDTO);
    FoodtruckOutputDTO toOutputDTO(Foodtruck entity);
}
