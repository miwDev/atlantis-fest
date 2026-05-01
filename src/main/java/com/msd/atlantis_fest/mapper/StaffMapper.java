package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "shifts", ignore = true)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    Staff toEntity(StaffInputDTO inputDTO);

    StaffOutputDTO toOutputDTO(Staff entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "shifts", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    void updateFromDTO(StaffInputDTO inputDTO, @MappingTarget Staff entity);
}