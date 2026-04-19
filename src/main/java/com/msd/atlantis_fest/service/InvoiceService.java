package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;

import java.util.List;

public interface InvoiceService {
    List<InvoiceOutputDTO> obtenerTodos();
    InvoiceOutputDTO obtenerPorId(Long id);
    InvoiceOutputDTO crear(InvoiceInputDTO inputDTO);
    InvoiceOutputDTO actualizar(Long id, InvoiceInputDTO inputDTO);
    boolean eliminar(Long id);
}
