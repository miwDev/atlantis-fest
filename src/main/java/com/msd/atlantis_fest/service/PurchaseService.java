package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    Page<PurchaseOutputDTO> obtenerTodos(Pageable pageable);
    PurchaseOutputDTO obtenerPorId(Long id);
    PurchaseOutputDTO crear(PurchaseInputDTO inputDTO);
    PurchaseOutputDTO actualizar(Long id, PurchaseInputDTO inputDTO);
    boolean eliminar(Long id);
}
