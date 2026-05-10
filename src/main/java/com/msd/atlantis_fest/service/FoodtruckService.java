package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FoodtruckService {
    Page<FoodtruckOutputDTO> obtenerTodos(Pageable pageable);
    FoodtruckOutputDTO obtenerPorId(Long id);
    FoodtruckOutputDTO crear(FoodtruckInputDTO inputDTO);
    FoodtruckOutputDTO actualizar(Long id, FoodtruckInputDTO inputDTO);
    void updateFoodtruckPhoto(Long id, MultipartFile file);
    void updateFoodtruckMenuPdf(Long id, MultipartFile file);
    byte[] getFoodtruckMenuPdf(Long id);
    boolean eliminar(Long id);
}
