package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewOutputDTO> obtenerTodos();
    ReviewOutputDTO obtenerPorId(Long id);
    ReviewOutputDTO crear(ReviewInputDTO inputDTO);
    ReviewOutputDTO actualizar(Long id, ReviewInputDTO inputDTO);
    boolean eliminar(Long id);
}
