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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private final StaffRepository staffRepository;
    private final ShiftRepository shiftRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
            Role artistRole = roleRepository.save(Role.builder().name("ARTIST").build());
            Role foodtruckRole = roleRepository.save(Role.builder().name("FOODTRUCK").build());
            Role clientRole = roleRepository.save(Role.builder().name("CLIENT").build());
            Role staffRole = roleRepository.save(Role.builder().name("STAFF").build());

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
                    .nombre("Atlantis Fest 2026")
                    .fechaInicio(LocalDate.now().plusMonths(6))
                    .fechaFin(LocalDate.now().plusMonths(6).plusDays(2))
                    .ubicacionGeneral("Isla de la Fantasía")
                    .build();
            festivalRepository.save(festival);

            Map<String, String[]> zonesToCreate = new LinkedHashMap<>();
            zonesToCreate.put("escenario-poseidon", new String[]{"Escenario Poseidón", "ESCENARIO"});
            zonesToCreate.put("vip-triton", new String[]{"Zona VIP Tritón", "VIP"});
            zonesToCreate.put("banos-oeste", new String[]{"Baños Sector Oeste", "BAÑOS"});
            zonesToCreate.put("foodtruck-nereo-1", new String[]{"Foodtruck 1", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-nereo-2", new String[]{"Foodtruck 2", "FOODTRUCK"});
            zonesToCreate.put("entrada-principal", new String[]{"Entrada Principal", "ENTRADA"});
            zonesToCreate.put("foodtruck-proteo-1", new String[]{"Foodtruck 3", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-proteo-2", new String[]{"Foodtruck 4", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-glauco-1", new String[]{"Foodtruck 5", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-glauco-2", new String[]{"Foodtruck 6", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-oceano-1", new String[]{"Foodtruck 7", "FOODTRUCK"});
            zonesToCreate.put("foodtruck-oceano-2", new String[]{"Foodtruck 8", "FOODTRUCK"});
            zonesToCreate.put("escenario-anfitrite", new String[]{"Escenario Anfitrite", "ESCENARIO"});
            zonesToCreate.put("vip-sirena", new String[]{"Zona VIP Sirena", "VIP"});
            zonesToCreate.put("banos-este", new String[]{"Baños Sector Este", "BAÑOS"});
            zonesToCreate.put("area-descanso", new String[]{"Área de Descanso", "GENERAL"});

            for (Map.Entry<String, String[]> entry : zonesToCreate.entrySet()) {
                String internalName = entry.getKey();
                String displayName = entry.getValue()[0];
                String zoneType = entry.getValue()[1];

                Zone zone = Zone.builder()
                        .nombre(internalName)
                        .descripcion(displayName)
                        .tipo(ZoneEnum.valueOf(zoneType))
                        .festival(festival)
                        .build();
                zoneRepository.save(zone);
            }

            Genre rock = Genre.builder().nombre("Rock").build();
            Genre pop = Genre.builder().nombre("Pop").build();
            Genre electronic = Genre.builder().nombre("Electronic").build();
            Genre indie = Genre.builder().nombre("Indie").build();
            Genre metal = Genre.builder().nombre("Metal").build();
            Genre hipHop = Genre.builder().nombre("Hip Hop").build();
            Genre reggaeton = Genre.builder().nombre("Reggaeton").build();
            Genre reggae = Genre.builder().nombre("Reggae").build();
            genreRepository.saveAll(Arrays.asList(rock, pop, electronic, indie, metal, hipHop, reggaeton, reggae));

            Artist artist1 = Artist.builder().username("artist1").email("arctic@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Alex").surname("Turner").artistName("Arctic Monkeys").biography("Banda de rock indie formada en Sheffield, Inglaterra.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(indie, rock)).build();
            Artist artist2 = Artist.builder().username("artist2").email("dua@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Dua").surname("Lipa").artistName("Dua Lipa").biography("Cantante y compositora británica-albanesa de pop.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(pop)).build();
            Artist artist3 = Artist.builder().username("artist3").email("david@atlantisfest.com").password(passwordEncoder.encode("12345")).name("David").surname("Guetta").artistName("David Guetta").biography("DJ y productor francés de música electrónica.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(electronic)).build();
            Artist artist4 = Artist.builder().username("artist4").email("rosalia@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Rosalía").surname("Vila").artistName("Rosalía").biography("Cantante española que fusiona flamenco con pop y urbano.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(pop, reggaeton)).build();
            Artist artist5 = Artist.builder().username("artist5").email("james@atlantisfest.com").password(passwordEncoder.encode("12345")).name("James").surname("Hetfield").artistName("Metallica").biography("Banda legendaria de heavy metal.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(metal, rock)).build();
            Artist artist6 = Artist.builder().username("artist6").email("kendrick@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Kendrick").surname("Lamar").artistName("Kendrick Lamar").biography("Rapero y compositor estadounidense, aclamado por la crítica.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(hipHop)).build();
            Artist artist7 = Artist.builder().username("artist7").email("bob@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Bob").surname("Marley").artistName("Bob Marley Tribute").biography("Banda tributo al rey del reggae.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(reggae)).build();
            Artist artist8 = Artist.builder().username("artist8").email("martin@atlantisfest.com").password(passwordEncoder.encode("12345")).name("Martin").surname("Garrix").artistName("Martin Garrix").biography("Joven DJ y productor neerlandés.").role(roleRepository.findByName("ARTIST").orElse(null)).genres(List.of(electronic)).build();
            
            artistRepository.saveAll(Arrays.asList(artist1, artist2, artist3, artist4, artist5, artist6, artist7, artist8));

            Foodtruck ft1 = Foodtruck.builder().username("ft1").email("ft1@atlantis.com").password(passwordEncoder.encode("12345")).nombre("Tacos El Primo").tipoComida("Mexicana").estaAbierto(true).zone(zoneRepository.findByNombre("foodtruck-nereo-1").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            Foodtruck ft2 = Foodtruck.builder().username("ft2").email("ft2@atlantis.com").password(passwordEncoder.encode("12345")).nombre("Burger Kong").tipoComida("Hamburguesas").estaAbierto(false).zone(zoneRepository.findByNombre("foodtruck-nereo-2").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            Foodtruck ft3 = Foodtruck.builder().username("ft3").email("ft3@atlantis.com").password(passwordEncoder.encode("12345")).nombre("Pizzas del Mar").tipoComida("Italiana").estaAbierto(true).zone(zoneRepository.findByNombre("foodtruck-proteo-1").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            Foodtruck ft4 = Foodtruck.builder().username("ft4").email("ft4@atlantis.com").password(passwordEncoder.encode("12345")).nombre("Sushi Wave").tipoComida("Japonesa").estaAbierto(true).zone(zoneRepository.findByNombre("foodtruck-proteo-2").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            Foodtruck ft5 = Foodtruck.builder().username("ft5").email("ft5@atlantis.com").password(passwordEncoder.encode("12345")).nombre("Kraken Smoothies").tipoComida("Bebidas").estaAbierto(true).zone(zoneRepository.findByNombre("foodtruck-glauco-1").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            Foodtruck ft6 = Foodtruck.builder().username("ft6").email("ft6@atlantis.com").password(passwordEncoder.encode("12345")).nombre("El Tesoro Dulce").tipoComida("Postres").estaAbierto(false).zone(zoneRepository.findByNombre("foodtruck-glauco-2").orElse(null)).role(roleRepository.findByName("FOODTRUCK").orElse(null)).build();
            foodtruckRepository.saveAll(Arrays.asList(ft1, ft2, ft3, ft4, ft5, ft6));

            Concert concert1 = Concert.builder().artist(artist1).zone(zoneRepository.findByNombre("escenario-poseidon").orElse(null)).fecha(festival.getFechaInicio()).horaInicio(LocalTime.of(20, 0)).horaFin(LocalTime.of(22, 0)).build();
            Concert concert2 = Concert.builder().artist(artist2).zone(zoneRepository.findByNombre("escenario-anfitrite").orElse(null)).fecha(festival.getFechaInicio()).horaInicio(LocalTime.of(22, 30)).horaFin(LocalTime.of(2, 0)).build();
            Concert concert3 = Concert.builder().artist(artist3).zone(zoneRepository.findByNombre("escenario-poseidon").orElse(null)).fecha(festival.getFechaInicio().plusDays(1)).horaInicio(LocalTime.of(23, 0)).horaFin(LocalTime.of(2, 0)).build();
            Concert concert4 = Concert.builder().artist(artist4).zone(zoneRepository.findByNombre("escenario-anfitrite").orElse(null)).fecha(festival.getFechaInicio().plusDays(1)).horaInicio(LocalTime.of(20, 0)).horaFin(LocalTime.of(22, 30)).build();
            Concert concert5 = Concert.builder().artist(artist5).zone(zoneRepository.findByNombre("escenario-poseidon").orElse(null)).fecha(festival.getFechaInicio().plusDays(2)).horaInicio(LocalTime.of(21, 0)).horaFin(LocalTime.of(23, 30)).build();
            Concert concert6 = Concert.builder().artist(artist6).zone(zoneRepository.findByNombre("escenario-anfitrite").orElse(null)).fecha(festival.getFechaInicio().plusDays(2)).horaInicio(LocalTime.of(19, 0)).horaFin(LocalTime.of(21, 0)).build();
            
            concertRepository.saveAll(Arrays.asList(concert1, concert2, concert3, concert4, concert5, concert6));

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

            Staff staff1 = Staff.builder()
                    .username("staff1")
                    .email("staff1@atlantisfest.com")
                    .password(passwordEncoder.encode("12345"))
                    .role(roleRepository.findByName("STAFF").orElse(null))
                    .build();
            staffRepository.save(staff1);

            Shift shift1 = Shift.builder()
                    .horaInicio(LocalDateTime.of(festival.getFechaInicio(), LocalTime.of(18, 0)))
                    .horaFin(LocalDateTime.of(festival.getFechaInicio(), LocalTime.of(23, 0)))
                    .descripcionTarea("Control de accesos en el escenario principal")
                    .staff(staff1)
                    .zone(zoneRepository.findByNombre("escenario-poseidon").orElse(null))
                    .build();

            Shift shift2 = Shift.builder()
                    .horaInicio(LocalDateTime.of(festival.getFechaInicio().plusDays(1), LocalTime.of(12, 0)))
                    .horaFin(LocalDateTime.of(festival.getFechaInicio().plusDays(1), LocalTime.of(18, 0)))
                    .descripcionTarea("Supervisión y limpieza en zona de foodtrucks")
                    .staff(staff1)
                    .zone(zoneRepository.findByNombre("foodtruck-nereo-1").orElse(null))
                    .build();
            shiftRepository.saveAll(Arrays.asList(shift1, shift2));

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
            System.out.println("- artist1 (ARTIST) - Arctic Monkeys");
            System.out.println("- artist2 (ARTIST) - Dua Lipa");
            System.out.println("- artist3 (ARTIST) - David Guetta");
            System.out.println("- artist4 (ARTIST) - Rosalía");
            System.out.println("- artist5 (ARTIST) - Metallica");
            System.out.println("- artist6 (ARTIST) - Kendrick Lamar");
            System.out.println("- artist7 (ARTIST) - Bob Marley Tribute");
            System.out.println("- artist8 (ARTIST) - Martin Garrix");
            System.out.println("- ft1 (FOODTRUCK) - Tacos El Primo");
            System.out.println("- ft2 (FOODTRUCK) - Burger Kong");
            System.out.println("- ft3 (FOODTRUCK) - Pizzas del Mar");
            System.out.println("- ft4 (FOODTRUCK) - Sushi Wave");
            System.out.println("- ft5 (FOODTRUCK) - Kraken Smoothies");
            System.out.println("- ft6 (FOODTRUCK) - El Tesoro Dulce");
            System.out.println("- client1 (CLIENT)");
            System.out.println("- staff1 (STAFF) - Asignado a 2 turnos");
            System.out.println("==================================================================");
        }
    }
}
