package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ReviewInputDTO;
import com.msd.atlantis_fest.dto.output.ReviewOutputDTO;
import com.msd.atlantis_fest.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resenas")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Page<ReviewOutputDTO>> obtenerResenas(Pageable pageable) {
        return ResponseEntity.ok(reviewService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewOutputDTO> obtenerResenaPorId(@PathVariable Long id) {
        ReviewOutputDTO review = reviewService.obtenerPorId(id);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ReviewOutputDTO> crearResena(@Valid @RequestBody ReviewInputDTO inputDTO) {
        return ResponseEntity.status(201).body(reviewService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewOutputDTO> actualizarResena(@PathVariable Long id, @Valid @RequestBody ReviewInputDTO inputDTO) {
        ReviewOutputDTO review = reviewService.actualizar(id, inputDTO);
        return review != null ? ResponseEntity.ok(review) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        boolean eliminado = reviewService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
