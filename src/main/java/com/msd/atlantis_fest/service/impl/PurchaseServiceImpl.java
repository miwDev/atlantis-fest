package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.PurchaseInputDTO;
import com.msd.atlantis_fest.dto.output.PurchaseOutputDTO;
import com.msd.atlantis_fest.entity.*;
import com.msd.atlantis_fest.mapper.PurchaseMapper;
import com.msd.atlantis_fest.repository.*;
import com.msd.atlantis_fest.service.PurchaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("Compra no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public PurchaseOutputDTO crear(PurchaseInputDTO inputDTO) {
        Client client = clientRepository.findById(inputDTO.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + inputDTO.getClientId()));

        TicketType ticketType = ticketTypeRepository.findById(inputDTO.getTicketTypeId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de ticket no encontrado con id: " + inputDTO.getTicketTypeId()));

        if (ticketType.getMaxDisponible() <= 0) {
            throw new IllegalStateException("No hay tickets disponibles para el tipo: " + ticketType.getTipo());
        }

        double precioFinal = ticketType.getPrecioBase() - (inputDTO.getDescuentoAplicado() != null ? inputDTO.getDescuentoAplicado() : 0.0);

        // 1. Crear la compra y guardarla para obtener un ID
        Purchase purchase = Purchase.builder()
                .client(client)
                .ticketType(ticketType)
                .fechaCompra(LocalDateTime.now())
                .precioFinal(precioFinal)
                .descuentoAplicado(inputDTO.getDescuentoAplicado())
                .build();
        Purchase savedPurchase = purchaseRepository.save(purchase);

        // 2. Crear y guardar el pago, asociándolo a la compra ya guardada
        Payment payment = Payment.builder()
                .monto(precioFinal)
                .metodoPago("CARD") // Asumimos un método de pago
                .estado("COMPLETED")
                .purchase(savedPurchase)
                .build();
        Payment savedPayment = paymentRepository.save(payment);

        // 3. Crear la factura
        Invoice invoice = Invoice.builder()
                .numeroFactura(UUID.randomUUID().toString())
                .fechaEmision(LocalDateTime.now())
                .datosFiscales(client.getDni() != null ? client.getDni() : "No especificado")
                .payment(savedPayment)
                .build();
        invoiceRepository.save(invoice);

        // 4. Actualizar el stock de tickets
        ticketType.setMaxDisponible(ticketType.getMaxDisponible() - 1);
        ticketTypeRepository.save(ticketType);

        return purchaseMapper.toOutputDTO(savedPurchase);
    }

    @Override
    public PurchaseOutputDTO actualizar(Long id, PurchaseInputDTO inputDTO) {
        // La lógica de negocio de una compra no suele permitir su actualización.
        // Se podría cancelar y crear una nueva. De momento, se deja sin implementación.
        throw new UnsupportedOperationException("La actualización de una compra no está permitida.");
    }

    @Override
    public boolean eliminar(Long id) {
        // La lógica de negocio de una compra no suele permitir su eliminación directa.
        // Se debería implementar una cancelación que, por ejemplo, devuelva el stock.
        throw new UnsupportedOperationException("La eliminación de una compra no está permitida.");
    }
}
