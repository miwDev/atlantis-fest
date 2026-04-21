package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShiftService {
    Page<ShiftOutputDTO> obtenerTodos(Pageable pageable);
    ShiftOutputDTO obtenerPorId(Long id);
    ShiftOutputDTO crear(ShiftInputDTO inputDTO);
    ShiftOutputDTO actualizar(Long id, ShiftInputDTO inputDTO);
    boolean eliminar(Long id);
}
