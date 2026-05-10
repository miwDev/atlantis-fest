package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.SocialMediaInputDTO;
import com.msd.atlantis_fest.dto.output.SocialMediaOutputDTO;
import com.msd.atlantis_fest.service.SocialMediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redes-sociales")
@RequiredArgsConstructor
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @GetMapping
    public ResponseEntity<Page<SocialMediaOutputDTO>> obtenerRedesSociales(Pageable pageable) {
        return ResponseEntity.ok(socialMediaService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialMediaOutputDTO> obtenerRedSocialPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socialMediaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SocialMediaOutputDTO> crearRedSocial(@Valid @RequestBody SocialMediaInputDTO inputDTO) {
        return ResponseEntity.status(201).body(socialMediaService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialMediaOutputDTO> actualizarRedSocial(@PathVariable Long id, @Valid @RequestBody SocialMediaInputDTO inputDTO) {
        return ResponseEntity.ok(socialMediaService.actualizar(id, inputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRedSocial(@PathVariable Long id) {
        boolean eliminado = socialMediaService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
