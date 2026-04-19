package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import com.msd.atlantis_fest.mapper.ZoneMapper;
import com.msd.atlantis_fest.repository.ZoneRepository;
import com.msd.atlantis_fest.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public List<ZoneOutputDTO> obtenerTodos() {
        return zoneRepository.findAll().stream()
                .map(zoneMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ZoneOutputDTO obtenerPorId(Long id) {
        return zoneRepository.findById(id)
                .map(zoneMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public ZoneOutputDTO crear(ZoneInputDTO inputDTO) {
        Zone zone = zoneMapper.toEntity(inputDTO);
        return zoneMapper.toOutputDTO(zoneRepository.save(zone));
    }

    @Override
    public ZoneOutputDTO actualizar(Long id, ZoneInputDTO inputDTO) {
        return zoneRepository.findById(id)
                .map(zone -> {
                    zoneMapper.updateFromDTO(inputDTO, zone);
                    return zoneMapper.toOutputDTO(zoneRepository.save(zone));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (zoneRepository.existsById(id)) {
            zoneRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
