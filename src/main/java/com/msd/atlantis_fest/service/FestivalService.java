package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;

import java.util.List;

public interface FestivalService {
    List<FestivalOutputDTO> obtenerTodos();
    FestivalOutputDTO obtenerPorId(Long id);
    FestivalOutputDTO crear(FestivalInputDTO inputDTO);
    FestivalOutputDTO actualizar(Long id, FestivalInputDTO inputDTO);
    boolean eliminar(Long id);
}
