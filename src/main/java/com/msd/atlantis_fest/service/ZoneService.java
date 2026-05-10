package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ZoneService {
    Page<ZoneOutputDTO> obtenerTodos(Pageable pageable);
    ZoneOutputDTO obtenerPorId(Long id);
    ZoneOutputDTO crear(ZoneInputDTO inputDTO);
    ZoneOutputDTO actualizar(Long id, ZoneInputDTO inputDTO);
    boolean eliminar(Long id);
    Page<ZoneOutputDTO> obtenerZonasPorFestival(Long festivalId, Pageable pageable);
    ZoneOutputDTO obtenerPorNombre(String nombre);
}
