package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;

import java.util.List;

public interface SocialMediaService {
    List<SocialMediaOutputDTO> obtenerTodos();
    SocialMediaOutputDTO obtenerPorId(Long id);
    SocialMediaOutputDTO crear(SocialMediaInputDTO inputDTO);
    SocialMediaOutputDTO actualizar(Long id, SocialMediaInputDTO inputDTO);
    boolean eliminar(Long id);
}
