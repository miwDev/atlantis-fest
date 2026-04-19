package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "shifts", ignore = true)
    Staff toEntity(StaffInputDTO inputDTO);

    StaffOutputDTO toOutputDTO(Staff entity);
}
