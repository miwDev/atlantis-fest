package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;
import com.msd.atlantis_fest.entity.SocialMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SocialMediaMapper {
    @Mapping(source = "artistId", target = "artist.id")
    @Mapping(target = "id", ignore = true)
    SocialMedia toEntity(SocialMediaInputDTO inputDTO);

    @Mapping(source = "artist.artistName", target = "artistName")
    SocialMediaOutputDTO toOutputDTO(SocialMedia entity);

    @Mapping(source = "artistId", target = "artist.id")
    @Mapping(target = "id", ignore = true)
    void updateFromDTO(SocialMediaInputDTO inputDTO, @MappingTarget SocialMedia entity);
}
