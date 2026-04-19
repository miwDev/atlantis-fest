package com.msd.atlantis_fest.api;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientOutputDTO>> obtenerClientes() {
        return ResponseEntity.ok(clientService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientOutputDTO> obtenerClientePorId(@PathVariable Long id) {
        ClientOutputDTO client = clientService.obtenerPorId(id);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClientOutputDTO> crearCliente(@RequestBody ClientInputDTO inputDTO) {
        return ResponseEntity.status(201).body(clientService.crear(inputDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientOutputDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClientInputDTO inputDTO) {
        ClientOutputDTO client = clientService.actualizar(id, inputDTO);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clientService.eliminar(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
