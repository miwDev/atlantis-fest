package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeSalesOutputDTO;
import com.msd.atlantis_fest.service.TicketTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-ticket")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @GetMapping
    public ResponseEntity<Page<TicketTypeOutputDTO>> obtenerTiposTicket(Pageable pageable) {
        return ResponseEntity.ok(ticketTypeService.obtenerTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeOutputDTO> obtenerTipoTicketPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketTypeService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TicketTypeOutputDTO> crearTipoTicket(@Valid @RequestBody TicketTypeInputDTO inputDTO) {
        return ResponseEntity.status(201).body(ticketTypeService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeOutputDTO> actualizarTipoTicket(@PathVariable Long id, @Valid @RequestBody TicketTypeInputDTO inputDTO) {
        return ResponseEntity.ok(ticketTypeService.actualizar(id, inputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoTicket(@PathVariable Long id) {
        boolean eliminado = ticketTypeService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/festival/{festivalId}")
    public ResponseEntity<Page<TicketTypeOutputDTO>> obtenerTiposTicketPorFestival(@PathVariable Long festivalId, Pageable pageable) {
        return ResponseEntity.ok(ticketTypeService.obtenerTiposTicketPorFestival(festivalId, pageable));
    }

    @GetMapping("/{id}/ventas")
    public ResponseEntity<TicketTypeSalesOutputDTO> obtenerVentasPorTicketId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketTypeService.obtenerVentasPorTicketId(id));
    }
}
