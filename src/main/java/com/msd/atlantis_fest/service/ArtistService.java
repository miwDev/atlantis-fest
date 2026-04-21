package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;

import java.util.List;

public interface ArtistService {
    List<ArtistOutputDTO> obtenerTodos();
    ArtistOutputDTO obtenerPorId(Long id);
    ArtistOutputDTO crear(ArtistInputDTO inputDTO);
    ArtistOutputDTO actualizar(Long id, ArtistInputDTO inputDTO);
    boolean eliminar(Long id);
}
