package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.entity.Foodtruck;
import com.msd.atlantis_fest.mapper.FoodtruckMapper;
import com.msd.atlantis_fest.repository.FoodtruckRepository;
import com.msd.atlantis_fest.service.FoodtruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService {

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtruckMapper foodtruckMapper;

    @Override
    public List<FoodtruckOutputDTO> obtenerTodos() {
        return foodtruckRepository.findAll().stream()
                .map(foodtruckMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FoodtruckOutputDTO obtenerPorId(Long id) {
        return foodtruckRepository.findById(id)
                .map(foodtruckMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public FoodtruckOutputDTO crear(FoodtruckInputDTO inputDTO) {
        Foodtruck foodtruck = foodtruckMapper.toEntity(inputDTO);
        return foodtruckMapper.toOutputDTO(foodtruckRepository.save(foodtruck));
    }

    @Override
    public FoodtruckOutputDTO actualizar(Long id, FoodtruckInputDTO inputDTO) {
        return foodtruckRepository.findById(id)
                .map(foodtruck -> {
                    foodtruckMapper.updateFromDTO(inputDTO, foodtruck);
                    return foodtruckMapper.toOutputDTO(foodtruckRepository.save(foodtruck));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (foodtruckRepository.existsById(id)) {
            foodtruckRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
