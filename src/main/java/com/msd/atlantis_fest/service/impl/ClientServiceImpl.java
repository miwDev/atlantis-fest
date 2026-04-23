package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ClientInputDTO;
import com.msd.atlantis_fest.dto.output.ClientOutputDTO;
import com.msd.atlantis_fest.entity.Client;
import com.msd.atlantis_fest.exception.custom.ResourceNotFoundException;
import com.msd.atlantis_fest.mapper.ClientMapper;
import com.msd.atlantis_fest.repository.ClientRepository;
import com.msd.atlantis_fest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<ClientOutputDTO> obtenerTodos(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toOutputDTO);
    }

    @Override
    public ClientOutputDTO obtenerPorId(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public ClientOutputDTO crear(ClientInputDTO inputDTO) {
        Client client = clientMapper.toEntity(inputDTO);
        client.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        return clientMapper.toOutputDTO(clientRepository.save(client));
    }

    @Override
    public ClientOutputDTO actualizar(Long id, ClientInputDTO inputDTO) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientMapper.updateFromDTO(inputDTO, client);
                    if (inputDTO.getPassword() != null && !inputDTO.getPassword().isEmpty()) {
                        client.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
                    }
                    return clientMapper.toOutputDTO(clientRepository.save(client));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public boolean eliminar(Long id) {
        boolean eliminado = false;
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            eliminado = true;
        }
        return eliminado;
    }
}
