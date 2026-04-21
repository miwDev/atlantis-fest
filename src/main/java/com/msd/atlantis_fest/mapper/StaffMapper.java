package com.msd.atlantis_fest.mapper;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.entity.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    Staff toEntity(StaffInputDTO inputDTO);

    StaffOutputDTO toOutputDTO(Staff entity);
}
