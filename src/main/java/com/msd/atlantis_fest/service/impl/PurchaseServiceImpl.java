package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import com.msd.atlantis_fest.entity.Purchase;
import com.msd.atlantis_fest.mapper.PurchaseMapper;
import com.msd.atlantis_fest.repository.PurchaseRepository;
import com.msd.atlantis_fest.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public List<PurchaseOutputDTO> obtenerTodos() {
        return purchaseRepository.findAll().stream()
                .map(purchaseMapper::toOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOutputDTO obtenerPorId(Long id) {
        return purchaseRepository.findById(id)
                .map(purchaseMapper::toOutputDTO)
                .orElse(null);
    }

    @Override
    public PurchaseOutputDTO crear(PurchaseInputDTO inputDTO) {
        Purchase purchase = purchaseMapper.toEntity(inputDTO);
        return purchaseMapper.toOutputDTO(purchaseRepository.save(purchase));
    }

    @Override
    public PurchaseOutputDTO actualizar(Long id, PurchaseInputDTO inputDTO) {
        return purchaseRepository.findById(id)
                .map(purchase -> {
                    purchaseMapper.updateFromDTO(inputDTO, purchase);
                    return purchaseMapper.toOutputDTO(purchaseRepository.save(purchase));
                })
                .orElse(null);
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
