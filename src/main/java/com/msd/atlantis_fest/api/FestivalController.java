package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.FestivalInputDTO;
import com.msd.atlantis_fest.dto.output.FestivalOutputDTO;
import com.msd.atlantis_fest.service.FestivalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/festivales")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping
    public ResponseEntity<Page<FestivalOutputDTO>> obtenerFestivales(Pageable pageable) {
        return ResponseEntity.ok(festivalService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FestivalOutputDTO> obtenerFestivalPorId(@PathVariable Long id) {
        FestivalOutputDTO festival = festivalService.obtenerPorId(id);
        return festival != null ? ResponseEntity.ok(festival) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FestivalOutputDTO> crearFestival(@Valid @RequestBody FestivalInputDTO inputDTO) {
        return ResponseEntity.status(201).body(festivalService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FestivalOutputDTO> actualizarFestival(@PathVariable Long id, @Valid @RequestBody FestivalInputDTO inputDTO) {
        FestivalOutputDTO festival = festivalService.actualizar(id, inputDTO);
        return festival != null ? ResponseEntity.ok(festival) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFestival(@PathVariable Long id) {
        boolean eliminado = festivalService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
