package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.entity.Artist;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ArtistMapper;
import com.msd.atlantis_fest.repository.ArtistRepository;
import com.msd.atlantis_fest.repository.RoleRepository;
import com.msd.atlantis_fest.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final RoleRepository roleRepository;
    private final ArtistMapper artistMapper;
    private final PasswordEncoder passwordEncoder;

    private final Path rootLocation = Paths.get("uploads");

    @Override
    public Page<ArtistOutputDTO> obtenerTodos(Pageable pageable) {
        return artistRepository.findAll(pageable)
                .map(artistMapper::toOutputDTO);
    }

    @Override
    public ArtistOutputDTO obtenerPorId(Long id) {
        return artistRepository.findById(id)
                .map(artistMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado con id: " + id));
    }

    @Override
    public ArtistOutputDTO crear(ArtistInputDTO inputDTO) {
        Artist artist = artistMapper.toEntity(inputDTO);
        artist.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        artist.setRole(roleRepository.findByName("ARTIST").orElseThrow(() -> new ResourceNotFoundException("Rol ARTIST no encontrado")));
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
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado con id: " + id));
    }

    @Override
    public void updateArtistPhoto(Long id, MultipartFile file) {

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la carpeta de almacenamiento", e);
        }

        if (file.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }

        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado con ID: " + id));

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String newFileName = UUID.randomUUID() + extension;

            if (artist.getFotoUrl() != null) {
                Path oldFilePath = rootLocation.resolve(artist.getFotoUrl());
                Files.deleteIfExists(oldFilePath);
            }

            Path destinationFile = rootLocation.resolve(Paths.get(newFileName)).normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            artist.setFotoUrl(newFileName);
            artistRepository.save(artist);

        } catch (IOException e) {
            throw new RuntimeException("Error al procesar el archivo: " + e.getMessage());
        }

    }

    @Override
    public boolean eliminar(Long id) throws IOException {
        boolean eliminado = false;

        Artist artistDb = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado con id: " + id));

        if (artistDb.getFotoUrl() != null) {
            Path oldFilePath = rootLocation.resolve(artistDb.getFotoUrl());
            Files.deleteIfExists(oldFilePath);
        }
        artistRepository.deleteById(id);
        eliminado = true;

        return eliminado;
    }
}
