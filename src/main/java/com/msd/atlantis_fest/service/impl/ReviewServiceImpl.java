package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import com.msd.atlantis_fest.entity.Review;
import com.msd.atlantis_fest.mapper.ReviewMapper;
import com.msd.atlantis_fest.repository.ReviewRepository;
import com.msd.atlantis_fest.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewOutputDTO> obtenerTodos() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewOutputDTO obtenerPorId(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public ReviewOutputDTO crear(ReviewInputDTO inputDTO) {
        Review review = reviewMapper.toEntity(inputDTO);
        return reviewMapper.toOutputDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewOutputDTO actualizar(Long id, ReviewInputDTO inputDTO) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewMapper.updateFromDTO(inputDTO, review);
                    return reviewMapper.toOutputDTO(reviewRepository.save(review));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
