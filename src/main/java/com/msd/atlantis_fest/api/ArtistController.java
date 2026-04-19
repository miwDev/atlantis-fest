package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.service.ArtistService;
import com.msd.atlantis_fest.service.ConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<List<ArtistOutputDTO>> obtenerArtistas() {
        return ResponseEntity.ok(artistService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistOutputDTO> obtenerArtistaPorId(@PathVariable Long id) {
        ArtistOutputDTO artist = artistService.obtenerPorId(id);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ArtistOutputDTO> crearArtista(@Valid @RequestBody ArtistInputDTO inputDTO) {
        return ResponseEntity.status(201).body(artistService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistOutputDTO> actualizarArtista(@PathVariable Long id, @Valid @RequestBody ArtistInputDTO inputDTO) {
        ArtistOutputDTO artist = artistService.actualizar(id, inputDTO);
        return artist != null ? ResponseEntity.ok(artist) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable Long id) {
        boolean eliminado = artistService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{artistId}/conciertos")
    public ResponseEntity<List<ConcertOutputDto>> obtenerConciertosPorArtista(@PathVariable Long artistId) {
        return ResponseEntity.ok(concertService.obtenerConciertosPorArtista(artistId));
    }
}
