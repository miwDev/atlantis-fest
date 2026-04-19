package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;
import com.msd.atlantis_fest.entity.Genre;
import com.msd.atlantis_fest.mapper.GenreMapper;
import com.msd.atlantis_fest.repository.GenreRepository;
import com.msd.atlantis_fest.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreOutputDTO> obtenerTodos() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GenreOutputDTO obtenerPorId(Long id) {
        return genreRepository.findById(id)
                .map(genreMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public GenreOutputDTO crear(GenreInputDTO inputDTO) {
        Genre genre = genreMapper.toEntity(inputDTO);
        return genreMapper.toOutputDTO(genreRepository.save(genre));
    }

    @Override
    public GenreOutputDTO actualizar(Long id, GenreInputDTO inputDTO) {
        return genreRepository.findById(id)
                .map(genre -> {
                    genreMapper.updateFromDTO(inputDTO, genre);
                    return genreMapper.toOutputDTO(genreRepository.save(genre));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
