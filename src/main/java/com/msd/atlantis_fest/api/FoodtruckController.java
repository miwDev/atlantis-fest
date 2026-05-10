package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.FoodtruckInputDTO;
import com.msd.atlantis_fest.dto.output.FoodtruckOutputDTO;
import com.msd.atlantis_fest.service.FoodtruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/foodtrucks")
@RequiredArgsConstructor
public class FoodtruckController {

    private final FoodtruckService foodtruckService;

    @GetMapping
    public ResponseEntity<Page<FoodtruckOutputDTO>> obtenerFoodtrucks(Pageable pageable) {
        return ResponseEntity.ok(foodtruckService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodtruckOutputDTO> obtenerFoodtruckPorId(@PathVariable Long id) {
        return ResponseEntity.ok(foodtruckService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<FoodtruckOutputDTO> crearFoodtruck(@Valid @RequestBody FoodtruckInputDTO inputDTO) {
        return ResponseEntity.status(201).body(foodtruckService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodtruckOutputDTO> actualizarFoodtruck(@PathVariable Long id, @Valid @RequestBody FoodtruckInputDTO inputDTO) {
        return ResponseEntity.ok(foodtruckService.actualizar(id, inputDTO));
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<Void> actualizarFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        foodtruckService.updateFoodtruckPhoto(id, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/menu")
    public ResponseEntity<Void> actualizarMenuPdf(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        foodtruckService.updateFoodtruckMenuPdf(id, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/menu")
    public ResponseEntity<byte[]> obtenerMenuPdf(@PathVariable Long id) {
        byte[] pdfBytes = foodtruckService.getFoodtruckMenuPdf(id);

        return ResponseEntity.ok()
                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"menu_" + id + ".pdf\"")
                .header(org.springframework.http.HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(pdfBytes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFoodtruck(@PathVariable Long id) {
        boolean eliminado = foodtruckService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
