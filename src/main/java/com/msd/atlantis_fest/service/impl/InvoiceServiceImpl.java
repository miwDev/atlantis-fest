package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.InvoiceInputDTO;
import com.msd.atlantis_fest.dto.output.InvoiceOutputDTO;
import com.msd.atlantis_fest.entity.Invoice;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.InvoiceMapper;
import com.msd.atlantis_fest.repository.InvoiceRepository;
import com.msd.atlantis_fest.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public Page<InvoiceOutputDTO> obtenerTodos(Pageable pageable) {
        return invoiceRepository.findAll(pageable)
                .map(invoiceMapper::toOutputDTO);
    }

    @Override
    public InvoiceOutputDTO obtenerPorId(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id: " + id));
    }

    @Override
    public InvoiceOutputDTO crear(InvoiceInputDTO inputDTO) {
        Invoice invoice = invoiceMapper.toEntity(inputDTO);
        return invoiceMapper.toOutputDTO(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceOutputDTO actualizar(Long id, InvoiceInputDTO inputDTO) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoiceMapper.updateFromDTO(inputDTO, invoice);
                    return invoiceMapper.toOutputDTO(invoiceRepository.save(invoice));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id: " + id));
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
