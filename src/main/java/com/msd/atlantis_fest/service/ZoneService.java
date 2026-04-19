package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;

import java.util.List;

public interface ZoneService {
    List<ZoneOutputDTO> obtenerTodos();
    ZoneOutputDTO obtenerPorId(Long id);
    ZoneOutputDTO crear(ZoneInputDTO inputDTO);
    ZoneOutputDTO actualizar(Long id, ZoneInputDTO inputDTO);
    boolean eliminar(Long id);
}
