package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FestivalService {
    Page<FestivalOutputDTO> obtenerTodos(Pageable pageable);
    FestivalOutputDTO obtenerPorId(Long id);
    FestivalOutputDTO crear(FestivalInputDTO inputDTO);
    FestivalOutputDTO actualizar(Long id, FestivalInputDTO inputDTO);
    boolean eliminar(Long id);
}
