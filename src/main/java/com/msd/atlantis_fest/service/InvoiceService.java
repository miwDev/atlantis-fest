package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<InvoiceOutputDTO> obtenerTodos(Pageable pageable);
    InvoiceOutputDTO obtenerPorId(Long id);
    InvoiceOutputDTO crear(InvoiceInputDTO inputDTO);
    InvoiceOutputDTO actualizar(Long id, InvoiceInputDTO inputDTO);
    boolean eliminar(Long id);
}
