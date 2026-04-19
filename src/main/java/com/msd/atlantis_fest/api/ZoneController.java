package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zonas")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping
    public ResponseEntity<List<ZoneOutputDTO>> obtenerZonas() {
        return ResponseEntity.ok(zoneService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneOutputDTO> obtenerZonaPorId(@PathVariable Long id) {
        ZoneOutputDTO zone = zoneService.obtenerPorId(id);
        return zone != null ? ResponseEntity.ok(zone) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ZoneOutputDTO> crearZona(@RequestBody ZoneInputDTO inputDTO) {
        return ResponseEntity.status(201).body(zoneService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZoneOutputDTO> actualizarZona(@PathVariable Long id, @RequestBody ZoneInputDTO inputDTO) {
        ZoneOutputDTO zone = zoneService.actualizar(id, inputDTO);
        return zone != null ? ResponseEntity.ok(zone) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarZona(@PathVariable Long id) {
        boolean eliminado = zoneService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/festival/{festivalId}")
    public ResponseEntity<List<ZoneOutputDTO>> obtenerZonasPorFestival(@PathVariable Long festivalId) {
        return ResponseEntity.ok(zoneService.obtenerZonasPorFestival(festivalId));
    }
}
