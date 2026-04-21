package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import com.msd.atlantis_fest.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewInputDTO inputDTO);

    @Mapping(source = "client.username", target = "clientUsername")
    ReviewOutputDTO toOutputDTO(Review entity);
}
