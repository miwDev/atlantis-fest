package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;
import com.msd.atlantis_fest.entity.SocialMedia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialMediaMapper {
    SocialMedia toEntity(SocialMediaInputDTO inputDTO);
    SocialMediaOutputDTO toOutputDTO(SocialMedia entity);
}
