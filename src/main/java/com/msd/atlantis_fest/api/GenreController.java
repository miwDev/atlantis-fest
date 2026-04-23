package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.GenreInputDTO;
import com.msd.atlantis_fest.dto.output.GenreOutputDTO;
import com.msd.atlantis_fest.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Page<GenreOutputDTO>> obtenerGeneros(Pageable pageable) {
        return ResponseEntity.ok(genreService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreOutputDTO> obtenerGeneroPorId(@PathVariable Long id) {
        GenreOutputDTO genre = genreService.obtenerPorId(id);
        return genre != null ? ResponseEntity.ok(genre) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GenreOutputDTO> crearGenero(@Valid @RequestBody GenreInputDTO inputDTO) {
        return ResponseEntity.status(201).body(genreService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreOutputDTO> actualizarGenero(@PathVariable Long id, @Valid @RequestBody GenreInputDTO inputDTO) {
        GenreOutputDTO genre = genreService.actualizar(id, inputDTO);
        return genre != null ? ResponseEntity.ok(genre) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Long id) {
        boolean eliminado = genreService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
