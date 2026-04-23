package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.entity.Concert;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ConcertMapper;
import com.msd.atlantis_fest.repository.ConcertRepository;
import com.msd.atlantis_fest.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertMapper concertMapper;

    @Override
    public Page<ConcertOutputDto> obtenerTodos(Pageable pageable) {
        return concertRepository.findAll(pageable)
                .map(concertMapper::toOutputDTO);
    }

    @Override
    public ConcertOutputDto obtenerPorId(Long id) {
        return concertRepository.findById(id)
                .map(concertMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Concierto no encontrado con id: " + id));
    }

    @Override
    public ConcertOutputDto crear(ConcertInputDTO inputDTO) {
        Concert concert = concertMapper.toEntity(inputDTO);
        return concertMapper.toOutputDTO(concertRepository.save(concert));
    }

    @Override
    public ConcertOutputDto actualizar(Long id, ConcertInputDTO inputDTO) {
        return concertRepository.findById(id)
                .map(concert -> {
                    concertMapper.updateFromDTO(inputDTO, concert);
                    return concertMapper.toOutputDTO(concertRepository.save(concert));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Concierto no encontrado con id: " + id));
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (concertRepository.existsById(id)) {
            concertRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }

    @Override
    public Page<ConcertOutputDto> obtenerConciertosPorArtista(Long artistId, Pageable pageable) {
        return concertRepository.findByArtistId(artistId, pageable)
                .map(concertMapper::toOutputDTO);
    }
}
