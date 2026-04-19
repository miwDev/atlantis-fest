package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;

import java.util.List;

public interface StaffService {
    List<StaffOutputDTO> obtenerTodos();
    StaffOutputDTO obtenerPorId(Long id);
    StaffOutputDTO crear(StaffInputDTO inputDTO);
    StaffOutputDTO actualizar(Long id, StaffInputDTO inputDTO);
    boolean eliminar(Long id);
}
