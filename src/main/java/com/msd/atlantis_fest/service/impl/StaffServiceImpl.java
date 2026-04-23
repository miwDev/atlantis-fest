package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.entity.Staff;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.StaffMapper;
import com.msd.atlantis_fest.repository.StaffRepository;
import com.msd.atlantis_fest.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<StaffOutputDTO> obtenerTodos(Pageable pageable) {
        return staffRepository.findAll(pageable)
                .map(staffMapper::toOutputDTO);
    }

    @Override
    public StaffOutputDTO obtenerPorId(Long id) {
        return staffRepository.findById(id)
                .map(staffMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Staff no encontrado con id: " + id));
    }

    @Override
    public StaffOutputDTO crear(StaffInputDTO inputDTO) {
        Staff staff = staffMapper.toEntity(inputDTO);
        staff.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        return staffMapper.toOutputDTO(staffRepository.save(staff));
    }

    @Override
    public StaffOutputDTO actualizar(Long id, StaffInputDTO inputDTO) {
        return staffRepository.findById(id)
                .map(staff -> {
                    staffMapper.updateFromDTO(inputDTO, staff);
                    if (inputDTO.getPassword() != null && !inputDTO.getPassword().isEmpty()) {
                        staff.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
                    }
                    return staffMapper.toOutputDTO(staffRepository.save(staff));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Staff no encontrado con id: " + id));
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
