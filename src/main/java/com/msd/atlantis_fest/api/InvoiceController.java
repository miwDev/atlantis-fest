package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import com.msd.atlantis_fest.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceOutputDTO>> obtenerFacturas() {
        return ResponseEntity.ok(invoiceService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceOutputDTO> obtenerFacturaPorId(@PathVariable Long id) {
        InvoiceOutputDTO invoice = invoiceService.obtenerPorId(id);
        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InvoiceOutputDTO> crearFactura(@Valid @RequestBody InvoiceInputDTO inputDTO) {
        return ResponseEntity.status(201).body(invoiceService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceOutputDTO> actualizarFactura(@PathVariable Long id, @Valid @RequestBody InvoiceInputDTO inputDTO) {
        InvoiceOutputDTO invoice = invoiceService.actualizar(id, inputDTO);
        return invoice != null ? ResponseEntity.ok(invoice) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        boolean eliminado = invoiceService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
