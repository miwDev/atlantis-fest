package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.StaffInputDTO;
import com.msd.atlantis_fest.dto.output.StaffOutputDTO;
import com.msd.atlantis_fest.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffOutputDTO>> obtenerStaff() {
        return ResponseEntity.ok(staffService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffOutputDTO> obtenerStaffPorId(@PathVariable Long id) {
        StaffOutputDTO staff = staffService.obtenerPorId(id);
        return staff != null ? ResponseEntity.ok(staff) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StaffOutputDTO> crearStaff(@RequestBody StaffInputDTO inputDTO) {
        return ResponseEntity.status(201).body(staffService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffOutputDTO> actualizarStaff(@PathVariable Long id, @RequestBody StaffInputDTO inputDTO) {
        StaffOutputDTO staff = staffService.actualizar(id, inputDTO);
        return staff != null ? ResponseEntity.ok(staff) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarStaff(@PathVariable Long id) {
        boolean eliminado = staffService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
