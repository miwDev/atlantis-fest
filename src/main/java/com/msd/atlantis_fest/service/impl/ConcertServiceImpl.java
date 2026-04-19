package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.entity.Concert;
import com.msd.atlantis_fest.mapper.ConcertMapper;
import com.msd.atlantis_fest.repository.ConcertRepository;
import com.msd.atlantis_fest.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertMapper concertMapper;

    @Override
    public List<ConcertOutputDto> obtenerTodos() {
        return concertRepository.findAll().stream()
                .map(concertMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConcertOutputDto obtenerPorId(Long id) {
        return concertRepository.findById(id)
                .map(concertMapper::toOutputDTO)
                .orElse(null);
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
                .orElse(null);
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
    public List<ConcertOutputDto> obtenerConciertosPorArtista(Long artistId) {
        return concertRepository.findByArtistId(artistId).stream()
                .map(concertMapper::toOutputDTO)
                .collect(Collectors.toList());
    }
}
