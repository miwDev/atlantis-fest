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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FoodtruckServiceImpl implements FoodtruckService {

    private final FoodtruckRepository foodtruckRepository;
    private final FoodtruckMapper foodtruckMapper;
    private final PasswordEncoder passwordEncoder;

    private final Path rootLocation = Paths.get("uploads");

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
    @Transactional
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
                    
                    // Si el frontend envía tieneMenuPdf explícitamente a false, significa que el usuario
                    // ha decidido eliminar el menú en el formulario de edición.
                    if (Boolean.FALSE.equals(inputDTO.getTieneMenuPdf())) {
                        foodtruck.setMenuPdf(null);
                    }

                    return foodtruckMapper.toOutputDTO(foodtruckRepository.save(foodtruck));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado con id: " + id));
    }

    @Override
    public void updateFoodtruckPhoto(Long id, MultipartFile file) {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Error en carpeta", e);
        }
        if (file.isEmpty()) throw new RuntimeException("Archivo vacío");

        Foodtruck foodtruck = foodtruckRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado"));
        try {
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = java.util.UUID.randomUUID().toString() + ext;
            if (foodtruck.getImagenPortadaUrl() != null) {
                Files.deleteIfExists(rootLocation.resolve(foodtruck.getImagenPortadaUrl()));
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(newFileName).normalize(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            foodtruck.setImagenPortadaUrl(newFileName);
            foodtruckRepository.save(foodtruck);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar", e);
        }
    }

    @Override
    public void updateFoodtruckMenuPdf(Long id, MultipartFile file) {
        if (file.isEmpty()) throw new RuntimeException("PDF vacío");
        Foodtruck foodtruck = foodtruckRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado"));
        try {
            foodtruck.setMenuPdf(file.getBytes());
            foodtruckRepository.save(foodtruck);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo PDF", e);
        }
    }

    @Override
    public byte[] getFoodtruckMenuPdf(Long id) {
        Foodtruck foodtruck = foodtruckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado con ID: " + id));

        if (foodtruck.getMenuPdf() == null) {
            throw new ResourceNotFoundException("El foodtruck no tiene un menú PDF asociado");
        }

        return foodtruck.getMenuPdf();
    }

    @Override
    public boolean eliminar(Long id) {
        Foodtruck foodtruckDb = foodtruckRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Foodtruck no encontrado"));
        if (foodtruckDb.getImagenPortadaUrl() != null) {
            try {
                Files.deleteIfExists(rootLocation.resolve(foodtruckDb.getImagenPortadaUrl()));
            } catch (IOException e) {
                System.err.println("No se borró foto física: " + e.getMessage());
            }
        }
        foodtruckRepository.deleteById(id);
        return true;
    }
}
