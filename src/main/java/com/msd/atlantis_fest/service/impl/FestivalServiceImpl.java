package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import com.msd.atlantis_fest.entity.Festival;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.FestivalMapper;
import com.msd.atlantis_fest.repository.FestivalRepository;
import com.msd.atlantis_fest.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;
    private final FestivalMapper festivalMapper;

    @Override
    public Page<FestivalOutputDTO> obtenerTodos(Pageable pageable) {
        return festivalRepository.findAll(pageable)
                .map(festivalMapper::toOutputDTO);
    }

    @Override
    public FestivalOutputDTO obtenerPorId(Long id) {
        return festivalRepository.findById(id)
                .map(festivalMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Festival no encontrado con id: " + id));
    }

    @Override
    public FestivalOutputDTO crear(FestivalInputDTO inputDTO) {
        Festival festival = festivalMapper.toEntity(inputDTO);
        return festivalMapper.toOutputDTO(festivalRepository.save(festival));
    }

    @Override
    public FestivalOutputDTO actualizar(Long id, FestivalInputDTO inputDTO) {
        return festivalRepository.findById(id)
                .map(festival -> {
                    festivalMapper.updateFromDTO(inputDTO, festival);
                    return festivalMapper.toOutputDTO(festivalRepository.save(festival));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Festival no encontrado con id: " + id));
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (festivalRepository.existsById(id)) {
            festivalRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
