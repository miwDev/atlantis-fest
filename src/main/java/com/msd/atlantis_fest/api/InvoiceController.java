package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import com.msd.atlantis_fest.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<Page<InvoiceOutputDTO>> obtenerFacturas(Pageable pageable) {
        return ResponseEntity.ok(invoiceService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceOutputDTO> obtenerFacturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<InvoiceOutputDTO> crearFactura(@Valid @RequestBody InvoiceInputDTO inputDTO) {
        return ResponseEntity.status(201).body(invoiceService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceOutputDTO> actualizarFactura(@PathVariable Long id, @Valid @RequestBody InvoiceInputDTO inputDTO) {
        return ResponseEntity.ok(invoiceService.actualizar(id, inputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        boolean eliminado = invoiceService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
