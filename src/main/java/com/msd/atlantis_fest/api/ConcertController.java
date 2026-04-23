package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.service.ConcertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conciertos")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping
    public ResponseEntity<Page<ConcertOutputDto>> obtenerConciertos(Pageable pageable) {
        return ResponseEntity.ok(concertService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcertOutputDto> obtenerConciertoPorId(@PathVariable Long id) {
        ConcertOutputDto concert = concertService.obtenerPorId(id);
        return concert != null ? ResponseEntity.ok(concert) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ConcertOutputDto> crearConcierto(@Valid @RequestBody ConcertInputDTO inputDTO) {
        return ResponseEntity.status(201).body(concertService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcertOutputDto> actualizarConcierto(@PathVariable Long id, @Valid @RequestBody ConcertInputDTO inputDTO) {
        ConcertOutputDto concert = concertService.actualizar(id, inputDTO);
        return concert != null ? ResponseEntity.ok(concert) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConcierto(@PathVariable Long id) {
        boolean eliminado = concertService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/artista/{artistId}")
    public ResponseEntity<Page<ConcertOutputDto>> obtenerConciertosPorArtista(@PathVariable Long artistId, Pageable pageable) {
        return ResponseEntity.ok(concertService.obtenerConciertosPorArtista(artistId, pageable));
    }
}
