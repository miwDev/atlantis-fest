package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
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
        TicketTypeOutputDTO ticketType = ticketTypeService.obtenerPorId(id);
        return ticketType != null ? ResponseEntity.ok(ticketType) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TicketTypeOutputDTO> crearTipoTicket(@Valid @RequestBody TicketTypeInputDTO inputDTO) {
        return ResponseEntity.status(201).body(ticketTypeService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeOutputDTO> actualizarTipoTicket(@PathVariable Long id, @Valid @RequestBody TicketTypeInputDTO inputDTO) {
        TicketTypeOutputDTO ticketType = ticketTypeService.actualizar(id, inputDTO);
        return ticketType != null ? ResponseEntity.ok(ticketType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoTicket(@PathVariable Long id) {
        boolean eliminado = ticketTypeService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
