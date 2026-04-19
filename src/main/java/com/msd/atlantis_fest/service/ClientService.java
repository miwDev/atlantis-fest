package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;

import java.util.List;

public interface ClientService {
    List<ClientOutputDTO> obtenerTodos();
    ClientOutputDTO obtenerPorId(Long id);
    ClientOutputDTO crear(ClientInputDTO inputDTO);
    ClientOutputDTO actualizar(Long id, ClientInputDTO inputDTO);
    boolean eliminar(Long id);
}
