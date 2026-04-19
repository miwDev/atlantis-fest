package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.service.TicketTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-ticket")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService ticketTypeService;

    @GetMapping
    public ResponseEntity<List<TicketTypeOutputDTO>> obtenerTiposTicket() {
        return ResponseEntity.ok(ticketTypeService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketTypeOutputDTO> obtenerTipoTicketPorId(@PathVariable Long id) {
        TicketTypeOutputDTO ticketType = ticketTypeService.obtenerPorId(id);
        return ticketType != null ? ResponseEntity.ok(ticketType) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TicketTypeOutputDTO> crearTipoTicket(@RequestBody TicketTypeInputDTO inputDTO) {
        return ResponseEntity.status(201).body(ticketTypeService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketTypeOutputDTO> actualizarTipoTicket(@PathVariable Long id, @RequestBody TicketTypeInputDTO inputDTO) {
        TicketTypeOutputDTO ticketType = ticketTypeService.actualizar(id, inputDTO);
        return ticketType != null ? ResponseEntity.ok(ticketType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoTicket(@PathVariable Long id) {
        boolean eliminado = ticketTypeService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
