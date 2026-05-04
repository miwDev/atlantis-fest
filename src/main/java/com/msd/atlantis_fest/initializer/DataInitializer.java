package com.msd.atlantis_fest.initializer;

import com.msd.atlantis_fest.entity.*;
import com.msd.atlantis_fest.enums.TicketEnum;
import com.msd.atlantis_fest.enums.ZoneEnum;
import com.msd.atlantis_fest.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FestivalRepository festivalRepository;
    private final ZoneRepository zoneRepository;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;
    private final FoodtruckRepository foodtruckRepository;
    private final ConcertRepository concertRepository;
    private final ClientRepository clientRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
            Role artistRole = roleRepository.save(Role.builder().name("ARTIST").build());
            Role foodtruckRole = roleRepository.save(Role.builder().name("FOODTRUCK").build());
            Role clientRole = roleRepository.save(Role.builder().name("CLIENT").build());

            User adminUser = User.builder()
                    .username("admin")
                    .email("admin@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(adminRole)
                    .build();
            userRepository.save(adminUser);
        }

        if (festivalRepository.count() == 0) {
            Festival festival = Festival.builder()
                    .nombre("Atlantis Fest 2024")
                    .fechaInicio(LocalDate.now().plusMonths(6))
                    .fechaFin(LocalDate.now().plusMonths(6).plusDays(2))
                    .ubicacionGeneral("Isla de la Fantasía")
                    .build();
            festivalRepository.save(festival);

            Zone mainStage = Zone.builder().nombre("Main Stage").descripcion("Escenario Principal").tipo(ZoneEnum.CONCIERTO).festival(festival).build();
            Zone electronicZone = Zone.builder().nombre("Electronic Zone").descripcion("Carpa Electrónica").tipo(ZoneEnum.CONCIERTO).festival(festival).build();
            Zone foodCourt = Zone.builder().nombre("Food Court").descripcion("Zona de Comidas y Bebidas").tipo(ZoneEnum.FOODTRUCK).festival(festival).build();
            Zone chillOut = Zone.builder().nombre("Chill Out Area").descripcion("Zona de descanso").tipo(ZoneEnum.DESCANSO).festival(festival).build();
            zoneRepository.saveAll(Arrays.asList(mainStage, electronicZone, foodCourt, chillOut));

            Genre rock = Genre.builder().nombre("Rock").build();
            Genre pop = Genre.builder().nombre("Pop").build();
            Genre electronic = Genre.builder().nombre("Electronic").build();
            Genre indie = Genre.builder().nombre("Indie").build();
            genreRepository.saveAll(Arrays.asList(rock, pop, electronic, indie));

            Artist artist1 = Artist.builder()
                    .username("artist1")
                    .email("artist1@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .name("John")
                    .surname("Doe")
                    .artistName("The Rockers")
                    .biography("Banda de rock alternativo con más de 10 años de experiencia.")
                    .role(roleRepository.findByName("ARTIST").orElse(null))
                    .genres(List.of(rock, indie))
                    .build();
                    
            Artist artist2 = Artist.builder()
                    .username("artist2")
                    .email("artist2@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .name("Jane")
                    .surname("Smith")
                    .artistName("DJ Jane")
                    .biography("DJ internacional de música electrónica y techno.")
                    .role(roleRepository.findByName("ARTIST").orElse(null))
                    .genres(List.of(electronic))
                    .build();
            artistRepository.saveAll(Arrays.asList(artist1, artist2));

            Foodtruck foodtruck1 = Foodtruck.builder()
                    .username("foodtruck1")
                    .email("foodtruck1@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .nombre("Tacos El Primo")
                    .tipoComida("Mexicana")
                    .estaAbierto(true)
                    .zone(foodCourt)
                    .role(roleRepository.findByName("FOODTRUCK").orElse(null))
                    .build();
                    
            Foodtruck foodtruck2 = Foodtruck.builder()
                    .username("foodtruck2")
                    .email("foodtruck2@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .nombre("Burger King Kong")
                    .tipoComida("Hamburguesas")
                    .estaAbierto(false)
                    .zone(foodCourt)
                    .role(roleRepository.findByName("FOODTRUCK").orElse(null))
                    .build();
            foodtruckRepository.saveAll(Arrays.asList(foodtruck1, foodtruck2));

            Concert concert1 = Concert.builder()
                    .artist(artist1)
                    .zone(mainStage)
                    .fecha(festival.getFechaInicio())
                    .horaInicio(LocalTime.of(20, 0))
                    .horaFin(LocalTime.of(22, 0))
                    .build();
                    
            Concert concert2 = Concert.builder()
                    .artist(artist2)
                    .zone(electronicZone)
                    .fecha(festival.getFechaInicio())
                    .horaInicio(LocalTime.of(22, 30))
                    .horaFin(LocalTime.of(2, 0))
                    .build();
            concertRepository.saveAll(Arrays.asList(concert1, concert2));

            Client client1 = Client.builder()
                    .username("client1")
                    .email("client1@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .nombre("Alice")
                    .apellidos("Wonderland")
                    .role(roleRepository.findByName("CLIENT").orElse(null))
                    .favoriteGenres(List.of(rock, pop))
                    .build();
            clientRepository.save(client1);

            TicketType general = TicketType.builder()
                    .tipo(TicketEnum.GENERAL)
                    .precioBase(50.00)
                    .descripcion("Acceso general de un día")
                    .maxDisponible(5000)
                    .festival(festival)
                    .build();
                    
            TicketType vip = TicketType.builder()
                    .tipo(TicketEnum.VIP)
                    .precioBase(150.00)
                    .descripcion("Acceso VIP con acceso a zonas exclusivas y Front Stage")
                    .maxDisponible(500)
                    .festival(festival)
                    .build();
                    
            TicketType abono = TicketType.builder()
                    .tipo(TicketEnum.ABONO_3_DIAS)
                    .precioBase(120.00)
                    .descripcion("Acceso para los 3 días del festival")
                    .maxDisponible(2000)
                    .festival(festival)
                    .build();
            ticketTypeRepository.saveAll(Arrays.asList(general, vip, abono));

            System.out.println("============== DATOS DE PRUEBA CREADOS EXITOSAMENTE ==============");
            System.out.println("Usuarios de prueba creados (todos con contraseña '12345'):");
            System.out.println("- admin (ADMIN)");
            System.out.println("- artist1 (ARTIST) - " + artist1.getArtistName());
            System.out.println("- artist2 (ARTIST) - " + artist2.getArtistName());
            System.out.println("- foodtruck1 (FOODTRUCK) - " + foodtruck1.getNombre());
            System.out.println("- foodtruck2 (FOODTRUCK) - " + foodtruck2.getNombre());
            System.out.println("- client1 (CLIENT)");
            System.out.println("==================================================================");
        }
    }
}