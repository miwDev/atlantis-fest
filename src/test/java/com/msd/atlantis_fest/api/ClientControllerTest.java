package com.msd.atlantis_fest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.service.ClientService;
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
class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ClientInputDTO clientInputDTO;
    private ClientOutputDTO clientOutputDTO;

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

        mockMvc = MockMvcBuilders.standaloneSetup(clientController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();

        clientInputDTO = new ClientInputDTO();
        clientInputDTO.setUsername("testclient");
        clientInputDTO.setPassword("password123");
        clientInputDTO.setEmail("client@test.com");
        clientInputDTO.setNombre("Test");
        clientInputDTO.setApellidos("Client");

        clientOutputDTO = new ClientOutputDTO();
        clientOutputDTO.setId(1L);
        clientOutputDTO.setUsername("testclient");
        clientOutputDTO.setNombre("Test");
    }

    @Test
    void obtenerTodos_Devuelve200() throws Exception {
        Page<ClientOutputDTO> page = new PageImpl<>(Collections.singletonList(clientOutputDTO));
        when(clientService.obtenerTodos(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L));
    }

    @Test
    void crearClienteDevuelve201() throws Exception {
        when(clientService.crear(any(ClientInputDTO.class))).thenReturn(clientOutputDTO);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testclient"));
    }

    @Test
    void obtenerClientePorIdDevuelve200() throws Exception {
        when(clientService.obtenerPorId(eq(1L))).thenReturn(clientOutputDTO);

        mockMvc.perform(get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Test"));
    }
}
