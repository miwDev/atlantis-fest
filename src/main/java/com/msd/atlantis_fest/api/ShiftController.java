package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import com.msd.atlantis_fest.service.ShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<Page<ShiftOutputDTO>> obtenerTurnos(Pageable pageable) {
        return ResponseEntity.ok(shiftService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftOutputDTO> obtenerTurnoPorId(@PathVariable Long id) {
        ShiftOutputDTO shift = shiftService.obtenerPorId(id);
        return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ShiftOutputDTO> crearTurno(@Valid @RequestBody ShiftInputDTO inputDTO) {
        return ResponseEntity.status(201).body(shiftService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftOutputDTO> actualizarTurno(@PathVariable Long id, @Valid @RequestBody ShiftInputDTO inputDTO) {
        ShiftOutputDTO shift = shiftService.actualizar(id, inputDTO);
        return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        boolean eliminado = shiftService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
