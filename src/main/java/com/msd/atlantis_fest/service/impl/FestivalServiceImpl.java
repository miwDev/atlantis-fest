package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import com.msd.atlantis_fest.entity.Festival;
import com.msd.atlantis_fest.mapper.FestivalMapper;
import com.msd.atlantis_fest.repository.FestivalRepository;
import com.msd.atlantis_fest.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;
    private final FestivalMapper festivalMapper;

    @Override
    public List<FestivalOutputDTO> obtenerTodos() {
        return festivalRepository.findAll().stream()
                .map(festivalMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FestivalOutputDTO obtenerPorId(Long id) {
        return festivalRepository.findById(id)
                .map(festivalMapper::toOutputDTO)
                .orElse(null);
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
                .orElse(null);
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
