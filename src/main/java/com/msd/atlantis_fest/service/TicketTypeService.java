package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeSalesOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketTypeService {
    Page<TicketTypeOutputDTO> obtenerTodos(Pageable pageable);
    TicketTypeOutputDTO obtenerPorId(Long id);
    TicketTypeOutputDTO crear(TicketTypeInputDTO inputDTO);
    TicketTypeOutputDTO actualizar(Long id, TicketTypeInputDTO inputDTO);
    boolean eliminar(Long id);
    
    Page<TicketTypeOutputDTO> obtenerTiposTicketPorFestival(Long festivalId, Pageable pageable);
    TicketTypeSalesOutputDTO obtenerVentasPorTicketId(Long id);
}