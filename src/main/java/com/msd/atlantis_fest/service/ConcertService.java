package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertService {
    Page<ConcertOutputDto> obtenerTodos(Pageable pageable);
    ConcertOutputDto obtenerPorId(Long id);
    ConcertOutputDto crear(ConcertInputDTO inputDTO);
    ConcertOutputDto actualizar(Long id, ConcertInputDTO inputDTO);
    boolean eliminar(Long id);
    Page<ConcertOutputDto> obtenerConciertosPorArtista(Long artistId, Pageable pageable);
}
