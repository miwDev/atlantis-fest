package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import com.msd.atlantis_fest.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    Review toEntity(ReviewInputDTO inputDTO);

    @Mapping(source = "client.username", target = "clientUsername")
    ReviewOutputDTO toOutputDTO(Review entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    void updateFromDTO(ReviewInputDTO inputDTO, @MappingTarget Review entity);
}
