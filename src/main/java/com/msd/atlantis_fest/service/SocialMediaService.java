package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocialMediaService {
    Page<SocialMediaOutputDTO> obtenerTodos(Pageable pageable);
    SocialMediaOutputDTO obtenerPorId(Long id);
    SocialMediaOutputDTO crear(SocialMediaInputDTO inputDTO);
    SocialMediaOutputDTO actualizar(Long id, SocialMediaInputDTO inputDTO);
    boolean eliminar(Long id);
}
