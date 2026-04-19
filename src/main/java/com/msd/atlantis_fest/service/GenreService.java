package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;

import java.util.List;

public interface GenreService {
    List<GenreOutputDTO> obtenerTodos();
    GenreOutputDTO obtenerPorId(Long id);
    GenreOutputDTO crear(GenreInputDTO inputDTO);
    GenreOutputDTO actualizar(Long id, GenreInputDTO inputDTO);
    boolean eliminar(Long id);
}
