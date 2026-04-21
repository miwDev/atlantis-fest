package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import com.msd.atlantis_fest.entity.Shift;
import com.msd.atlantis_fest.mapper.ShiftMapper;
import com.msd.atlantis_fest.repository.ShiftRepository;
import com.msd.atlantis_fest.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {

    private final ShiftRepository shiftRepository;
    private final ShiftMapper shiftMapper;

    @Override
    public Page<ShiftOutputDTO> obtenerTodos(Pageable pageable) {
        return shiftRepository.findAll(pageable)
                .map(shiftMapper::toOutputDTO);
    }

    @Override
    public ShiftOutputDTO obtenerPorId(Long id) {
        return shiftRepository.findById(id)
                .map(shiftMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public ShiftOutputDTO crear(ShiftInputDTO inputDTO) {
        Shift shift = shiftMapper.toEntity(inputDTO);
        return shiftMapper.toOutputDTO(shiftRepository.save(shift));
    }

    @Override
    public ShiftOutputDTO actualizar(Long id, ShiftInputDTO inputDTO) {
        return shiftRepository.findById(id)
                .map(shift -> {
                    shiftMapper.updateFromDTO(inputDTO, shift);
                    return shiftMapper.toOutputDTO(shiftRepository.save(shift));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (shiftRepository.existsById(id)) {
            shiftRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
