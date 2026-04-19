package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.entity.Staff;
import com.msd.atlantis_fest.mapper.StaffMapper;
import com.msd.atlantis_fest.repository.StaffRepository;
import com.msd.atlantis_fest.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public List<StaffOutputDTO> obtenerTodos() {
        return staffRepository.findAll().stream()
                .map(staffMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StaffOutputDTO obtenerPorId(Long id) {
        return staffRepository.findById(id)
                .map(staffMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public StaffOutputDTO crear(StaffInputDTO inputDTO) {
        Staff staff = staffMapper.toEntity(inputDTO);
        return staffMapper.toOutputDTO(staffRepository.save(staff));
    }

    @Override
    public StaffOutputDTO actualizar(Long id, StaffInputDTO inputDTO) {
        return staffRepository.findById(id)
                .map(staff -> {
                    staffMapper.updateFromDTO(inputDTO, staff);
                    return staffMapper.toOutputDTO(staffRepository.save(staff));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
