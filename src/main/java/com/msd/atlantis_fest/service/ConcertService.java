package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;

import java.util.List;

public interface ConcertService {
    List<ConcertOutputDto> obtenerTodos();
    ConcertOutputDto obtenerPorId(Long id);
    ConcertOutputDto crear(ConcertInputDTO inputDTO);
    ConcertOutputDto actualizar(Long id, ConcertInputDTO inputDTO);
    boolean eliminar(Long id);
    List<ConcertOutputDto> obtenerConciertosPorArtista(Long artistId);
}
