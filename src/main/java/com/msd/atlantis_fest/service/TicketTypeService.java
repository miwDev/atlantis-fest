package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;

import java.util.List;

public interface TicketTypeService {
    List<TicketTypeOutputDTO> obtenerTodos();
    TicketTypeOutputDTO obtenerPorId(Long id);
    TicketTypeOutputDTO crear(TicketTypeInputDTO inputDTO);
    TicketTypeOutputDTO actualizar(Long id, TicketTypeInputDTO inputDTO);
    boolean eliminar(Long id);
}
