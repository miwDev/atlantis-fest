package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import com.msd.atlantis_fest.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<Page<PurchaseOutputDTO>> obtenerCompras(Pageable pageable) {
        return ResponseEntity.ok(purchaseService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOutputDTO> obtenerCompraPorId(@PathVariable Long id) {
        PurchaseOutputDTO purchase = purchaseService.obtenerPorId(id);
        return purchase != null ? ResponseEntity.ok(purchase) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PurchaseOutputDTO> crearCompra(@Valid @RequestBody PurchaseInputDTO inputDTO) {
        return ResponseEntity.status(201).body(purchaseService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOutputDTO> actualizarCompra(@PathVariable Long id, @Valid @RequestBody PurchaseInputDTO inputDTO) {
        PurchaseOutputDTO purchase = purchaseService.actualizar(id, inputDTO);
        return purchase != null ? ResponseEntity.ok(purchase) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        boolean eliminado = purchaseService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<Page<PurchaseOutputDTO>> obtenerComprasPorCliente(@PathVariable Long clientId, Pageable pageable) {
        return ResponseEntity.ok(purchaseService.obtenerPorCliente(clientId, pageable));
    }
}
