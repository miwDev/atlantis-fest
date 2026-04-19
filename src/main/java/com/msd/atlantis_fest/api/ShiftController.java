package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ShiftInputDTO;
import com.msd.atlantis_fest.dto.output.ShiftOutputDTO;
import com.msd.atlantis_fest.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<ShiftOutputDTO>> obtenerTurnos() {
        return ResponseEntity.ok(shiftService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftOutputDTO> obtenerTurnoPorId(@PathVariable Long id) {
        ShiftOutputDTO shift = shiftService.obtenerPorId(id);
        return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ShiftOutputDTO> crearTurno(@RequestBody ShiftInputDTO inputDTO) {
        return ResponseEntity.status(201).body(shiftService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShiftOutputDTO> actualizarTurno(@PathVariable Long id, @RequestBody ShiftInputDTO inputDTO) {
        ShiftOutputDTO shift = shiftService.actualizar(id, inputDTO);
        return shift != null ? ResponseEntity.ok(shift) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        boolean eliminado = shiftService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
