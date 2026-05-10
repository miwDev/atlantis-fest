package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ArtistService {
    Page<ArtistOutputDTO> obtenerTodos(Pageable pageable);
    ArtistOutputDTO obtenerPorId(Long id);
    ArtistOutputDTO crear(ArtistInputDTO inputDTO);
    ArtistOutputDTO actualizar(Long id, ArtistInputDTO inputDTO);
    void updateArtistPhoto(Long id, MultipartFile file);
    boolean eliminar(Long id) throws IOException;
}
