package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffService {
    Page<StaffOutputDTO> obtenerTodos(Pageable pageable);
    StaffOutputDTO obtenerPorId(Long id);
    StaffOutputDTO crear(StaffInputDTO inputDTO);
    StaffOutputDTO actualizar(Long id, StaffInputDTO inputDTO);
    boolean eliminar(Long id);
}
