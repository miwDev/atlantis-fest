package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.entity.Zone;
import com.msd.atlantis_fest.enums.ZoneEnum;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ZoneMapper;
import com.msd.atlantis_fest.repository.ZoneRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZoneServiceImplTest {

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private ZoneMapper zoneMapper;

    @InjectMocks
    private ZoneServiceImpl zoneService;

    private Zone zone;
    private ZoneInputDTO zoneInputDTO;
    private ZoneOutputDTO zoneOutputDTO;

    @BeforeEach
    void setUp() {
        zone = new Zone();
        zone.setId(1L);
        zone.setNombre("Main Stage");
        zone.setTipo(ZoneEnum.CONCIERTO);

        zoneInputDTO = new ZoneInputDTO();
        zoneInputDTO.setNombre("Main Stage");
        zoneInputDTO.setTipo(ZoneEnum.CONCIERTO);

        zoneOutputDTO = new ZoneOutputDTO();
        zoneOutputDTO.setId(1L);
        zoneOutputDTO.setNombre("Main Stage");
        zoneOutputDTO.setTipo(ZoneEnum.CONCIERTO);
    }

    @Test
    void obtenerTodos_DevuelvePaginaDeZonas() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Zone> zonePage = new PageImpl<>(Collections.singletonList(zone));

        when(zoneRepository.findAll(pageable)).thenReturn(zonePage);
        when(zoneMapper.toOutputDTO(any(Zone.class))).thenReturn(zoneOutputDTO);

        Page<ZoneOutputDTO> result = zoneService.obtenerTodos(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Main Stage", result.getContent().get(0).getNombre());
        verify(zoneRepository, times(1)).findAll(pageable);
    }

    @Test
    void obtenerPorId_DevuelveZonaSiExiste() {
        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(zoneMapper.toOutputDTO(any(Zone.class))).thenReturn(zoneOutputDTO);

        ZoneOutputDTO result = zoneService.obtenerPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Main Stage", result.getNombre());
    }

    @Test
    void obtenerPorId_LanzaExcepcionSiNoExiste() {
        when(zoneRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> zoneService.obtenerPorId(99L));
    }

    @Test
    void crear_GuardaYDevuelveZona() {
        when(zoneMapper.toEntity(any(ZoneInputDTO.class))).thenReturn(zone);
        when(zoneRepository.save(any(Zone.class))).thenReturn(zone);
        when(zoneMapper.toOutputDTO(any(Zone.class))).thenReturn(zoneOutputDTO);

        ZoneOutputDTO result = zoneService.crear(zoneInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Main Stage", result.getNombre());
        verify(zoneRepository, times(1)).save(zone);
    }

    @Test
    void actualizar_ActualizaYDevuelveZonaSiExiste() {
        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(zoneRepository.save(any(Zone.class))).thenReturn(zone);
        when(zoneMapper.toOutputDTO(any(Zone.class))).thenReturn(zoneOutputDTO);

        ZoneOutputDTO result = zoneService.actualizar(1L, zoneInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(zoneMapper, times(1)).updateFromDTO(zoneInputDTO, zone);
        verify(zoneRepository, times(1)).save(zone);
    }

    @Test
    void actualizar_LanzaExcepcionSiNoExiste() {
        when(zoneRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> zoneService.actualizar(99L, zoneInputDTO));
    }

    @Test
    void eliminar_BorraZonaYDevuelveTrueSiExiste() {
        when(zoneRepository.existsById(1L)).thenReturn(true);

        boolean result = zoneService.eliminar(1L);

        assertTrue(result);
        verify(zoneRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminar_DevuelveFalseSiNoExiste() {
        when(zoneRepository.existsById(99L)).thenReturn(false);

        boolean result = zoneService.eliminar(99L);

        assertFalse(result);
        verify(zoneRepository, never()).deleteById(anyLong());
    }
}
