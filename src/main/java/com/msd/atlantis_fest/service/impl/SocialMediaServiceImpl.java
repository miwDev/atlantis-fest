package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;
import com.msd.atlantis_fest.entity.SocialMedia;
import com.msd.atlantis_fest.mapper.SocialMediaMapper;
import com.msd.atlantis_fest.repository.SocialMediaRepository;
import com.msd.atlantis_fest.service.SocialMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialMediaServiceImpl implements SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;
    private final SocialMediaMapper socialMediaMapper;

    @Override
    public List<SocialMediaOutputDTO> obtenerTodos() {
        return socialMediaRepository.findAll().stream()
                .map(socialMediaMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SocialMediaOutputDTO obtenerPorId(Long id) {
        return socialMediaRepository.findById(id)
                .map(socialMediaMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public SocialMediaOutputDTO crear(SocialMediaInputDTO inputDTO) {
        SocialMedia socialMedia = socialMediaMapper.toEntity(inputDTO);
        return socialMediaMapper.toOutputDTO(socialMediaRepository.save(socialMedia));
    }

    @Override
    public SocialMediaOutputDTO actualizar(Long id, SocialMediaInputDTO inputDTO) {
        return socialMediaRepository.findById(id)
                .map(socialMedia -> {
                    socialMediaMapper.updateFromDTO(inputDTO, socialMedia);
                    return socialMediaMapper.toOutputDTO(socialMediaRepository.save(socialMedia));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (socialMediaRepository.existsById(id)) {
            socialMediaRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
