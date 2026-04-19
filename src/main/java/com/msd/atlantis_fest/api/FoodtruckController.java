package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.service.FoodtruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodtrucks")
@RequiredArgsConstructor
public class FoodtruckController {

    private final FoodtruckService foodtruckService;

    @GetMapping
    public ResponseEntity<List<FoodtruckOutputDTO>> obtenerFoodtrucks() {
        return ResponseEntity.ok(foodtruckService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodtruckOutputDTO> obtenerFoodtruckPorId(@PathVariable Long id) {
        FoodtruckOutputDTO foodtruck = foodtruckService.obtenerPorId(id);
        return foodtruck != null ? ResponseEntity.ok(foodtruck) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FoodtruckOutputDTO> crearFoodtruck(@RequestBody FoodtruckInputDTO inputDTO) {
        return ResponseEntity.status(201).body(foodtruckService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodtruckOutputDTO> actualizarFoodtruck(@PathVariable Long id, @RequestBody FoodtruckInputDTO inputDTO) {
        FoodtruckOutputDTO foodtruck = foodtruckService.actualizar(id, inputDTO);
        return foodtruck != null ? ResponseEntity.ok(foodtruck) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFoodtruck(@PathVariable Long id) {
        boolean eliminado = foodtruckService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
