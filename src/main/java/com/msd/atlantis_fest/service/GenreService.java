package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    Page<GenreOutputDTO> obtenerTodos(Pageable pageable);
    GenreOutputDTO obtenerPorId(Long id);
    GenreOutputDTO crear(GenreInputDTO inputDTO);
    GenreOutputDTO actualizar(Long id, GenreInputDTO inputDTO);
    boolean eliminar(Long id);
}
