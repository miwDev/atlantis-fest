package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.entity.Client;
import com.msd.atlantis_fest.entity.Role;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ClientMapper;
import com.msd.atlantis_fest.repository.ClientRepository;
import com.msd.atlantis_fest.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private ClientInputDTO clientInputDTO;
    private ClientOutputDTO clientOutputDTO;
    private Role clientRole;

    @BeforeEach
    void setUp() {
        clientRole = Role.builder().id(1L).name("CLIENT").build();

        clientInputDTO = new ClientInputDTO();
        clientInputDTO.setUsername("testclient");
        clientInputDTO.setPassword("password123");
        clientInputDTO.setEmail("client@test.com");
        clientInputDTO.setNombre("Test");
        clientInputDTO.setApellidos("Client");

        client = new Client();
        client.setId(1L);
        client.setUsername("testclient");
        client.setNombre("Test");

        clientOutputDTO = new ClientOutputDTO();
        clientOutputDTO.setId(1L);
        clientOutputDTO.setUsername("testclient");
        clientOutputDTO.setNombre("Test");
    }

    @Test
    void crearClienteAsignaRolCorrectamente() {
        when(clientMapper.toEntity(any(ClientInputDTO.class))).thenReturn(client);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(roleRepository.findByName("CLIENT")).thenReturn(Optional.of(clientRole));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toOutputDTO(any(Client.class))).thenReturn(clientOutputDTO);

        ClientOutputDTO result = clientService.crear(clientInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testclient", result.getUsername());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void obtenerPorIdLanzaExcepcionSiNoExiste() {
        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.obtenerPorId(99L));
    }
}
