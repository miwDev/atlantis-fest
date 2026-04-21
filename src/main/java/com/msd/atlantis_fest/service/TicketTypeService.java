package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketTypeService {
    Page<TicketTypeOutputDTO> obtenerTodos(Pageable pageable);
    TicketTypeOutputDTO obtenerPorId(Long id);
    TicketTypeOutputDTO crear(TicketTypeInputDTO inputDTO);
    TicketTypeOutputDTO actualizar(Long id, TicketTypeInputDTO inputDTO);
    boolean eliminar(Long id);
}
