package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.service.ArtistService;
import com.msd.atlantis_fest.service.ConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/artistas")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<Page<ArtistOutputDTO>> obtenerArtistas(Pageable pageable) {
        return ResponseEntity.ok(artistService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistOutputDTO> obtenerArtistaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ArtistOutputDTO> crearArtista(@Valid @RequestBody ArtistInputDTO inputDTO) {
        return ResponseEntity.status(201).body(artistService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistOutputDTO> actualizarArtista(@PathVariable Long id, @Valid @RequestBody ArtistInputDTO inputDTO) {
        return ResponseEntity.ok(artistService.actualizar(id, inputDTO));
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<Void> actualizarFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        artistService.updateArtistPhoto(id, file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Long id) throws IOException {
        boolean eliminado = artistService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{artistId}/conciertos")
    public ResponseEntity<Page<ConcertOutputDto>> obtenerConciertosPorArtista(@PathVariable Long artistId, Pageable pageable) {
        return ResponseEntity.ok(concertService.obtenerConciertosPorArtista(artistId, pageable));
    }
}
