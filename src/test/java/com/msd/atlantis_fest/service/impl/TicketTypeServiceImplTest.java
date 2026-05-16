package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeSalesOutputDTO;
import com.msd.atlantis_fest.entity.TicketType;
import com.msd.atlantis_fest.enums.TicketEnum;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.TicketTypeMapper;
import com.msd.atlantis_fest.repository.PurchaseRepository;
import com.msd.atlantis_fest.repository.TicketTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketTypeServiceImplTest {

    @Mock
    private TicketTypeRepository ticketTypeRepository;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private TicketTypeMapper ticketTypeMapper;

    @InjectMocks
    private TicketTypeServiceImpl ticketTypeService;

    private TicketType ticketType;
    private TicketTypeInputDTO ticketTypeInputDTO;
    private TicketTypeOutputDTO ticketTypeOutputDTO;

    @BeforeEach
    void setUp() {
        ticketType = new TicketType();
        ticketType.setId(1L);
        ticketType.setTipo(TicketEnum.GENERAL);
        ticketType.setPrecioBase(50.0);
        ticketType.setMaxDisponible(100);

        ticketTypeInputDTO = new TicketTypeInputDTO();
        ticketTypeInputDTO.setTipo(TicketEnum.GENERAL);
        ticketTypeInputDTO.setPrecioBase(50.0);
        ticketTypeInputDTO.setMaxDisponible(100);
        ticketTypeInputDTO.setFestivalId(1L);

        ticketTypeOutputDTO = new TicketTypeOutputDTO();
        ticketTypeOutputDTO.setId(1L);
        ticketTypeOutputDTO.setTipo(TicketEnum.GENERAL);
        ticketTypeOutputDTO.setPrecioBase(50.0);
        ticketTypeOutputDTO.setMaxDisponible(100);
    }

    @Test
    void obtenerTodos_DevuelvePagina() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TicketType> page = new PageImpl<>(Collections.singletonList(ticketType));

        when(ticketTypeRepository.findAll(pageable)).thenReturn(page);
        when(ticketTypeMapper.toOutputDTO(any(TicketType.class))).thenReturn(ticketTypeOutputDTO);

        Page<TicketTypeOutputDTO> result = ticketTypeService.obtenerTodos(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void obtenerPorId_DevuelveTicketType() {
        when(ticketTypeRepository.findById(1L)).thenReturn(Optional.of(ticketType));
        when(ticketTypeMapper.toOutputDTO(any(TicketType.class))).thenReturn(ticketTypeOutputDTO);

        TicketTypeOutputDTO result = ticketTypeService.obtenerPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void obtenerPorId_LanzaExcepcion() {
        when(ticketTypeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> ticketTypeService.obtenerPorId(99L));
    }

    @Test
    void crear_GuardaYDevuelveTicketType() {
        when(ticketTypeMapper.toEntity(any(TicketTypeInputDTO.class))).thenReturn(ticketType);
        when(ticketTypeRepository.save(any(TicketType.class))).thenReturn(ticketType);
        when(ticketTypeMapper.toOutputDTO(any(TicketType.class))).thenReturn(ticketTypeOutputDTO);

        TicketTypeOutputDTO result = ticketTypeService.crear(ticketTypeInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketTypeRepository, times(1)).save(ticketType);
    }

    @Test
    void actualizar_ActualizaYDevuelveTicketType() {
        when(ticketTypeRepository.findById(1L)).thenReturn(Optional.of(ticketType));
        when(ticketTypeRepository.save(any(TicketType.class))).thenReturn(ticketType);
        when(ticketTypeMapper.toOutputDTO(any(TicketType.class))).thenReturn(ticketTypeOutputDTO);

        TicketTypeOutputDTO result = ticketTypeService.actualizar(1L, ticketTypeInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ticketTypeMapper, times(1)).updateFromDTO(ticketTypeInputDTO, ticketType);
        verify(ticketTypeRepository, times(1)).save(ticketType);
    }

    @Test
    void eliminar_DevuelveTrueSiSeElimina() {
        when(ticketTypeRepository.existsById(1L)).thenReturn(true);

        boolean result = ticketTypeService.eliminar(1L);

        assertTrue(result);
        verify(ticketTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminar_DevuelveFalseSiNoExiste() {
        when(ticketTypeRepository.existsById(99L)).thenReturn(false);

        boolean result = ticketTypeService.eliminar(99L);

        assertFalse(result);
        verify(ticketTypeRepository, never()).deleteById(anyLong());
    }

    @Test
    void obtenerTiposTicketPorFestival_DevuelvePagina() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TicketType> page = new PageImpl<>(Collections.singletonList(ticketType));

        when(ticketTypeRepository.findByFestivalId(1L, pageable)).thenReturn(page);
        when(ticketTypeMapper.toOutputDTO(any(TicketType.class))).thenReturn(ticketTypeOutputDTO);

        Page<TicketTypeOutputDTO> result = ticketTypeService.obtenerTiposTicketPorFestival(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void obtenerVentasPorTicketId_CalculaCorrectamente() {
        when(ticketTypeRepository.findById(1L)).thenReturn(Optional.of(ticketType));
        when(purchaseRepository.countByTicketTypeId(1L)).thenReturn(20L);
        when(purchaseRepository.sumPrecioFinalByTicketTypeId(1L)).thenReturn(1000.0);

        TicketTypeSalesOutputDTO result = ticketTypeService.obtenerVentasPorTicketId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getTicketTypeId());
        assertEquals("GENERAL", result.getTipo());
        assertEquals(20L, result.getVendidos());
        assertEquals(80L, result.getDisponibles()); // 100 - 20
        assertEquals(1000.0, result.getIngresoTotal());
    }

    @Test
    void obtenerVentasPorTicketId_SinVentas_DevuelveCero() {
        when(ticketTypeRepository.findById(1L)).thenReturn(Optional.of(ticketType));
        when(purchaseRepository.countByTicketTypeId(1L)).thenReturn(0L);
        when(purchaseRepository.sumPrecioFinalByTicketTypeId(1L)).thenReturn(null);

        TicketTypeSalesOutputDTO result = ticketTypeService.obtenerVentasPorTicketId(1L);

        assertNotNull(result);
        assertEquals(0L, result.getVendidos());
        assertEquals(100L, result.getDisponibles());
        assertEquals(0.0, result.getIngresoTotal());
    }
}
