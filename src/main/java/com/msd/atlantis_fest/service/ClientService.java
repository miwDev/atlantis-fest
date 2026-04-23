package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientOutputDTO> obtenerTodos(Pageable pageable);
    ClientOutputDTO obtenerPorId(Long id);
    ClientOutputDTO crear(ClientInputDTO inputDTO);
    ClientOutputDTO actualizar(Long id, ClientInputDTO inputDTO);
    boolean eliminar(Long id);
}
