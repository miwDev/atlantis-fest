package com.msd.atlantis_fest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.atlantis_fest.dto.input.ZoneInputDTO;
import com.msd.atlantis_fest.dto.output.ZoneOutputDTO;
import com.msd.atlantis_fest.enums.ZoneEnum;
import com.msd.atlantis_fest.service.ZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ZoneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ZoneService zoneService;

    @InjectMocks
    private ZoneController zoneController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ZoneInputDTO zoneInputDTO;
    private ZoneOutputDTO zoneOutputDTO;

    @BeforeEach
    void setUp() {
        // Configuración para que MockMvc entienda Pageable y pueda serializar Page
        var pageModule = new com.fasterxml.jackson.databind.module.SimpleModule();
        pageModule.addSerializer(Page.class, new com.fasterxml.jackson.databind.JsonSerializer<Page>() {
            @Override
            public void serialize(Page value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider serializers) throws java.io.IOException {
                gen.writeStartObject();
                gen.writeObjectField("content", value.getContent());
                gen.writeNumberField("totalElements", value.getTotalElements());
                gen.writeNumberField("totalPages", value.getTotalPages());
                gen.writeNumberField("number", value.getNumber());
                gen.writeEndObject();
            }
        });
        objectMapper.registerModule(pageModule);

        mockMvc = MockMvcBuilders.standaloneSetup(zoneController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();

        zoneInputDTO = new ZoneInputDTO();
        zoneInputDTO.setNombre("Main Stage");
        zoneInputDTO.setTipo(ZoneEnum.ESCENARIO);
        zoneInputDTO.setFestivalId(1L);

        zoneOutputDTO = new ZoneOutputDTO();
        zoneOutputDTO.setId(1L);
        zoneOutputDTO.setNombre("Main Stage");
        zoneOutputDTO.setTipo(ZoneEnum.ESCENARIO);
    }

    @Test
    void obtenerZonas_Devuelve200YPagina() throws Exception {
        Page<ZoneOutputDTO> page = new PageImpl<>(Collections.singletonList(zoneOutputDTO));
        when(zoneService.obtenerTodos(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/zonas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].nombre").value("Main Stage"));
    }

    @Test
    void obtenerZonaPorId_Devuelve200SiExiste() throws Exception {
        when(zoneService.obtenerPorId(eq(1L))).thenReturn(zoneOutputDTO);

        mockMvc.perform(get("/zonas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Main Stage"));
    }

    @Test
    void crearZona_Devuelve201() throws Exception {
        when(zoneService.crear(any(ZoneInputDTO.class))).thenReturn(zoneOutputDTO);

        mockMvc.perform(post("/zonas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(zoneInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Main Stage"));
    }

    @Test
    void actualizarZona_Devuelve200() throws Exception {
        when(zoneService.actualizar(eq(1L), any(ZoneInputDTO.class))).thenReturn(zoneOutputDTO);

        mockMvc.perform(put("/zonas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(zoneInputDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Main Stage"));
    }

    @Test
    void eliminarZona_Devuelve204SiElimina() throws Exception {
        when(zoneService.eliminar(eq(1L))).thenReturn(true);

        mockMvc.perform(delete("/zonas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarZona_Devuelve404SiNoExiste() throws Exception {
        when(zoneService.eliminar(eq(99L))).thenReturn(false);

        mockMvc.perform(delete("/zonas/99"))
                .andExpect(status().isNotFound());
    }
}
