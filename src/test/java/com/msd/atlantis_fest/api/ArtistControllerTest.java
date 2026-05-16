package com.msd.atlantis_fest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.service.ArtistService;
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
class ArtistControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ArtistService artistService;

    @Mock
    private ConcertService concertService;

    @InjectMocks
    private ArtistController artistController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ArtistInputDTO artistInputDTO;
    private ArtistOutputDTO artistOutputDTO;

    @BeforeEach
    void setUp() {
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

        mockMvc = MockMvcBuilders.standaloneSetup(artistController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();

        artistInputDTO = new ArtistInputDTO();
        artistInputDTO.setUsername("testuser");
        artistInputDTO.setPassword("password123");
        artistInputDTO.setEmail("test@test.com");
        artistInputDTO.setName("Test");
        artistInputDTO.setSurname("User");
        artistInputDTO.setArtistName("Test Artist");

        artistOutputDTO = new ArtistOutputDTO();
        artistOutputDTO.setId(1L);
        artistOutputDTO.setUsername("testuser");
        artistOutputDTO.setArtistName("Test Artist");
    }

    @Test
    void obtenerArtistas_Devuelve200() throws Exception {
        Page<ArtistOutputDTO> page = new PageImpl<>(Collections.singletonList(artistOutputDTO));
        when(artistService.obtenerTodos(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/artistas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    void obtenerArtistaPorId_Devuelve200() throws Exception {
        when(artistService.obtenerPorId(eq(1L))).thenReturn(artistOutputDTO);

        mockMvc.perform(get("/artistas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void crearArtista_Devuelve201() throws Exception {
        when(artistService.crear(any(ArtistInputDTO.class))).thenReturn(artistOutputDTO);

        mockMvc.perform(post("/artistas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(artistInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.artistName").value("Test Artist"));
    }
}
