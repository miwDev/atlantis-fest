package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import com.msd.atlantis_fest.entity.Review;
import com.msd.atlantis_fest.mapper.ReviewMapper;
import com.msd.atlantis_fest.repository.ReviewRepository;
import com.msd.atlantis_fest.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Page<ReviewOutputDTO> obtenerTodos(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(reviewMapper::toOutputDTO);
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
