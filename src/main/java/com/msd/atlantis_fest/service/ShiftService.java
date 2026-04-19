package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;

import java.util.List;

public interface ShiftService {
    List<ShiftOutputDTO> obtenerTodos();
    ShiftOutputDTO obtenerPorId(Long id);
    ShiftOutputDTO crear(ShiftInputDTO inputDTO);
    ShiftOutputDTO actualizar(Long id, ShiftInputDTO inputDTO);
    boolean eliminar(Long id);
}
