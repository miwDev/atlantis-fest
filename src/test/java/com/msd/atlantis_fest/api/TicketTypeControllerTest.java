package com.msd.atlantis_fest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msd.atlantis_fest.dto.input.TicketTypeInputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeOutputDTO;
import com.msd.atlantis_fest.dto.output.TicketTypeSalesOutputDTO;
import com.msd.atlantis_fest.enums.TicketEnum;
import com.msd.atlantis_fest.service.TicketTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TicketTypeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketTypeService ticketTypeService;

    @InjectMocks
    private TicketTypeController ticketTypeController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private TicketTypeSalesOutputDTO ticketTypeSalesOutputDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketTypeController).build();

        ticketTypeSalesOutputDTO = TicketTypeSalesOutputDTO.builder()
                .ticketTypeId(1L)
                .tipo(TicketEnum.GENERAL.name())
                .precioBase(50.0)
                .vendidos(20L)
                .disponibles(80L)
                .ingresoTotal(1000.0)
                .build();
    }

    @Test
    void obtenerVentasPorTicketId_Devuelve200YMetricas() throws Exception {
        when(ticketTypeService.obtenerVentasPorTicketId(eq(1L))).thenReturn(ticketTypeSalesOutputDTO);

        mockMvc.perform(get("/tipos-ticket/1/ventas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticketTypeId").value(1L))
                .andExpect(jsonPath("$.vendidos").value(20))
                .andExpect(jsonPath("$.ingresoTotal").value(1000.0));
    }
}
