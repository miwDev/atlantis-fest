package com.msd.atlantis_fest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msd.atlantis_fest.dto.input.ConcertInputDTO;
import com.msd.atlantis_fest.dto.output.ConcertOutputDto;
import com.msd.atlantis_fest.service.ConcertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ConcertControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ConcertService concertService;

    @InjectMocks
    private ConcertController concertController;

    private ObjectMapper objectMapper;

    private ConcertInputDTO concertInputDTO;
    private ConcertOutputDto concertOutputDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(concertController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Necesario para LocalDate y LocalTime

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
    void obtenerConciertos_Devuelve200() throws Exception {
        Page<ConcertOutputDto> page = new PageImpl<>(Collections.singletonList(concertOutputDTO));
        when(concertService.obtenerTodos(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/conciertos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    void obtenerConciertoPorId_Devuelve200() throws Exception {
        when(concertService.obtenerPorId(eq(1L))).thenReturn(concertOutputDTO);

        mockMvc.perform(get("/conciertos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void crearConcierto_Devuelve201() throws Exception {
        when(concertService.crear(any(ConcertInputDTO.class))).thenReturn(concertOutputDTO);

        mockMvc.perform(post("/conciertos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(concertInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void actualizarConcierto_Devuelve200() throws Exception {
        when(concertService.actualizar(eq(1L), any(ConcertInputDTO.class))).thenReturn(concertOutputDTO);

        mockMvc.perform(put("/conciertos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(concertInputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void eliminarConcierto_Devuelve204SiElimina() throws Exception {
        when(concertService.eliminar(eq(1L))).thenReturn(true);

        mockMvc.perform(delete("/conciertos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarConcierto_Devuelve404SiNoExiste() throws Exception {
        when(concertService.eliminar(eq(99L))).thenReturn(false);

        mockMvc.perform(delete("/conciertos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerConciertosPorArtista_Devuelve200() throws Exception {
        Page<ConcertOutputDto> page = new PageImpl<>(Collections.singletonList(concertOutputDTO));
        when(concertService.obtenerConciertosPorArtista(eq(1L), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/conciertos/artista/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }
}
