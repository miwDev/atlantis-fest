package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.entity.Foodtruck;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.FoodtruckMapper;
import com.msd.atlantis_fest.repository.FoodtruckRepository;
import com.msd.atlantis_fest.service.FoodtruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService {

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtruckMapper foodtruckMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<FoodtruckOutputDTO> obtenerTodos(Pageable pageable) {
        return foodtruckRepository.findAll(pageable)
                .map(foodtruckMapper::toOutputDTO);
    }

    @Override
    public FoodtruckOutputDTO obtenerPorId(Long id) {
        return foodtruckRepository.findById(id)
                .map(foodtruckMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado con id: " + id));
    }

    @Override
    public FoodtruckOutputDTO crear(FoodtruckInputDTO inputDTO) {
        Foodtruck foodtruck = foodtruckMapper.toEntity(inputDTO);
        foodtruck.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        return foodtruckMapper.toOutputDTO(foodtruckRepository.save(foodtruck));
    }

    @Override
    public FoodtruckOutputDTO actualizar(Long id, FoodtruckInputDTO inputDTO) {
        return foodtruckRepository.findById(id)
                .map(foodtruck -> {
                    foodtruckMapper.updateFromDTO(inputDTO, foodtruck);
                    if (inputDTO.getPassword() != null && !inputDTO.getPassword().isEmpty()) {
                        foodtruck.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
                    }
                    return foodtruckMapper.toOutputDTO(foodtruckRepository.save(foodtruck));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado con id: " + id));
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
