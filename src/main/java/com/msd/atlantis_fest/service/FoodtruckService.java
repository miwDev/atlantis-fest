package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;

import java.util.List;

public interface FoodtruckService {
    List<FoodtruckOutputDTO> obtenerTodos();
    FoodtruckOutputDTO obtenerPorId(Long id);
    FoodtruckOutputDTO crear(FoodtruckInputDTO inputDTO);
    FoodtruckOutputDTO actualizar(Long id, FoodtruckInputDTO inputDTO);
    boolean eliminar(Long id);
}
