package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<ReviewOutputDTO> obtenerTodos(Pageable pageable);
    ReviewOutputDTO obtenerPorId(Long id);
    ReviewOutputDTO crear(ReviewInputDTO inputDTO);
    ReviewOutputDTO actualizar(Long id, ReviewInputDTO inputDTO);
    boolean eliminar(Long id);
}
