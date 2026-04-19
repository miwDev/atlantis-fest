package com.msd.atlantis_fest.service;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;

import java.util.List;

public interface PurchaseService {
    List<PurchaseOutputDTO> obtenerTodos();
    PurchaseOutputDTO obtenerPorId(Long id);
    PurchaseOutputDTO crear(PurchaseInputDTO inputDTO);
    PurchaseOutputDTO actualizar(Long id, PurchaseInputDTO inputDTO);
    boolean eliminar(Long id);
}
