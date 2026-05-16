package com.msd.atlantis_fest.service.impl;

import com.msd.atlantis_fest.dto.input.ArtistInputDTO;
import com.msd.atlantis_fest.dto.output.ArtistOutputDTO;
import com.msd.atlantis_fest.entity.Artist;
import com.msd.atlantis_fest.entity.Role;
import com.msd.atlantis_fest.mapper.ArtistMapper;
import com.msd.atlantis_fest.repository.ArtistRepository;
import com.msd.atlantis_fest.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ArtistMapper artistMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ArtistServiceImpl artistService;

    private Artist artist;
    private ArtistInputDTO artistInputDTO;
    private ArtistOutputDTO artistOutputDTO;
    private Role artistRole;

    @BeforeEach
    void setUp() {
        artistRole = Role.builder().id(1L).name("ARTIST").build();
        
        artistInputDTO = new ArtistInputDTO();
        artistInputDTO.setUsername("testuser");
        artistInputDTO.setPassword("password");
        artistInputDTO.setEmail("test@test.com");
        artistInputDTO.setName("Test");
        artistInputDTO.setSurname("User");
        artistInputDTO.setArtistName("Test Artist");

        artist = new Artist();
        artist.setId(1L);
        artist.setUsername("testuser");
        artist.setArtistName("Test Artist");
        
        artistOutputDTO = new ArtistOutputDTO();
        artistOutputDTO.setId(1L);
        artistOutputDTO.setUsername("testuser");
        artistOutputDTO.setArtistName("Test Artist");
    }

    @Test
    void crearArtistaCorrectamente() {
        when(artistMapper.toEntity(any(ArtistInputDTO.class))).thenReturn(artist);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(roleRepository.findByName("ARTIST")).thenReturn(Optional.of(artistRole));
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);
        when(artistMapper.toOutputDTO(any(Artist.class))).thenReturn(artistOutputDTO);

        ArtistOutputDTO result = artistService.crear(artistInputDTO);

        assertNotNull(result);
        assertEquals(artistOutputDTO.getId(), result.getId());
        assertEquals(artistOutputDTO.getUsername(), result.getUsername());
        assertEquals("encodedPassword", artist.getPassword());
        assertEquals(artistRole, artist.getRole());
    }
}
