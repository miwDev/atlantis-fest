package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.entity.Concert;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ConcertMapper;
import com.msd.atlantis_fest.repository.ConcertRepository;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConcertServiceImplTest {

    @Mock
    private ConcertRepository concertRepository;

    @Mock
    private ConcertMapper concertMapper;

    @InjectMocks
    private ConcertServiceImpl concertService;

    private Concert concert;
    private ConcertInputDTO concertInputDTO;
    private ConcertOutputDto concertOutputDTO;

    @BeforeEach
    void setUp() {
        concert = new Concert();
        concert.setId(1L);
        concert.setFecha(LocalDate.now());
        concert.setHoraInicio(LocalTime.of(20, 0));
        concert.setHoraFin(LocalTime.of(22, 0));

        concertInputDTO = new ConcertInputDTO();
        concertInputDTO.setArtistId(1L);
        concertInputDTO.setZoneId(1L);
        concertInputDTO.setFecha(LocalDate.now().plusDays(1));
        concertInputDTO.setHoraInicio(LocalTime.of(20, 0));
        concertInputDTO.setHoraFin(LocalTime.of(22, 0));

        concertOutputDTO = new ConcertOutputDto();
        concertOutputDTO.setId(1L);
        concertOutputDTO.setArtistName("Artist Name");
    }

    @Test
    void obtenerTodos_DevuelvePagina() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Concert> concertPage = new PageImpl<>(Collections.singletonList(concert));

        when(concertRepository.findAll(pageable)).thenReturn(concertPage);
        when(concertMapper.toOutputDTO(any(Concert.class))).thenReturn(concertOutputDTO);

        Page<ConcertOutputDto> result = concertService.obtenerTodos(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void obtenerPorId_DevuelveConcierto() {
        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));
        when(concertMapper.toOutputDTO(any(Concert.class))).thenReturn(concertOutputDTO);

        ConcertOutputDto result = concertService.obtenerPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void obtenerPorId_NoEncontrado_LanzaExcepcion() {
        when(concertRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> concertService.obtenerPorId(99L));
    }

    @Test
    void crear_GuardaYDevuelveConcierto() {
        when(concertMapper.toEntity(any(ConcertInputDTO.class))).thenReturn(concert);
        when(concertRepository.save(any(Concert.class))).thenReturn(concert);
        when(concertMapper.toOutputDTO(any(Concert.class))).thenReturn(concertOutputDTO);

        ConcertOutputDto result = concertService.crear(concertInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(concertRepository, times(1)).save(concert);
    }

    @Test
    void actualizar_ActualizaYDevuelveConcierto() {
        when(concertRepository.findById(1L)).thenReturn(Optional.of(concert));
        when(concertRepository.save(any(Concert.class))).thenReturn(concert);
        when(concertMapper.toOutputDTO(any(Concert.class))).thenReturn(concertOutputDTO);

        ConcertOutputDto result = concertService.actualizar(1L, concertInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(concertMapper, times(1)).updateFromDTO(concertInputDTO, concert);
    }

    @Test
    void eliminar_BorraYDevuelveTrue() {
        when(concertRepository.existsById(1L)).thenReturn(true);

        boolean result = concertService.eliminar(1L);

        assertTrue(result);
        verify(concertRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminar_DevuelveFalseSiNoExiste() {
        when(concertRepository.existsById(99L)).thenReturn(false);

        boolean result = concertService.eliminar(99L);

        assertFalse(result);
        verify(concertRepository, never()).deleteById(anyLong());
    }

    @Test
    void obtenerConciertosPorArtista_DevuelvePagina() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Concert> concertPage = new PageImpl<>(Collections.singletonList(concert));

        when(concertRepository.findByArtistId(1L, pageable)).thenReturn(concertPage);
        when(concertMapper.toOutputDTO(any(Concert.class))).thenReturn(concertOutputDTO);

        Page<ConcertOutputDto> result = concertService.obtenerConciertosPorArtista(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }
}
