package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.entity.TicketType;
import com.msd.atlantis_fest.mapper.TicketTypeMapper;
import com.msd.atlantis_fest.repository.TicketTypeRepository;
import com.msd.atlantis_fest.service.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;
    private final TicketTypeMapper ticketTypeMapper;

    @Override
    public List<TicketTypeOutputDTO> obtenerTodos() {
        return ticketTypeRepository.findAll().stream()
                .map(ticketTypeMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TicketTypeOutputDTO obtenerPorId(Long id) {
        return ticketTypeRepository.findById(id)
                .map(ticketTypeMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public TicketTypeOutputDTO crear(TicketTypeInputDTO inputDTO) {
        TicketType ticketType = ticketTypeMapper.toEntity(inputDTO);
        return ticketTypeMapper.toOutputDTO(ticketTypeRepository.save(ticketType));
    }

    @Override
    public TicketTypeOutputDTO actualizar(Long id, TicketTypeInputDTO inputDTO) {
        return ticketTypeRepository.findById(id)
                .map(ticketType -> {
                    ticketTypeMapper.updateFromDTO(inputDTO, ticketType);
                    return ticketTypeMapper.toOutputDTO(ticketTypeRepository.save(ticketType));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (ticketTypeRepository.existsById(id)) {
            ticketTypeRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
