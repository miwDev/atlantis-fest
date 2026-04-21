package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import com.msd.atlantis_fest.mapper.ZoneMapper;
import com.msd.atlantis_fest.repository.ZoneRepository;
import com.msd.atlantis_fest.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public Page<ZoneOutputDTO> obtenerTodos(Pageable pageable) {
        return zoneRepository.findAll(pageable)
                .map(zoneMapper::toOutputDTO);
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

    @Override
    public Page<ZoneOutputDTO> obtenerZonasPorFestival(Long festivalId, Pageable pageable) {
        return zoneRepository.findByFestivalId(festivalId, pageable)
                .map(zoneMapper::toOutputDTO);
    }
}
