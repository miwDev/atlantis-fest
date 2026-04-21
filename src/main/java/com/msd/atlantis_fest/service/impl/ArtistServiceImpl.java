package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.entity.Artist;
import com.msd.atlantis_fest.mapper.ArtistMapper;
import com.msd.atlantis_fest.repository.ArtistRepository;
import com.msd.atlantis_fest.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ArtistOutputDTO> obtenerTodos() {
        return artistRepository.findAll().stream()
                .map(artistMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistOutputDTO obtenerPorId(Long id) {
        return artistRepository.findById(id)
                .map(artistMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public ArtistOutputDTO crear(ArtistInputDTO inputDTO) {
        Artist artist = artistMapper.toEntity(inputDTO);
        artist.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        return artistMapper.toOutputDTO(artistRepository.save(artist));
    }

    @Override
    public ArtistOutputDTO actualizar(Long id, ArtistInputDTO inputDTO) {
        return artistRepository.findById(id)
                .map(artist -> {
                    artistMapper.updateFromDTO(inputDTO, artist);
                    if (inputDTO.getPassword() != null && !inputDTO.getPassword().isEmpty()) {
                        artist.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
                    }
                    return artistMapper.toOutputDTO(artistRepository.save(artist));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
