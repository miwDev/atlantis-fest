package com.msd.atlantis_fest.initializer;

import com.msd.atlantis_fest.entity.Role;
import com.msd.atlantis_fest.entity.User;
import com.msd.atlantis_fest.repository.RoleRepository;
import com.msd.atlantis_fest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Inicializar un rol por defecto si no existe
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder()
                    .name("ADMIN")
                    .build();
            roleRepository.save(adminRole);

            // Inicializar un usuario de prueba si no existe
            if (userRepository.count() == 0) {
                User defaultUser = User.builder()
                        .username("admin")
                        .email("admin@atlantisfest.com")
                        // Contraseña sin encriptar es "12345"
                        .password(passwordEncoder.encode("12345"))
                        .role(adminRole)
                        .build();
                userRepository.save(defaultUser);
                System.out.println("============== USUARIO DE PRUEBA CREADO ==============");
                System.out.println("Username: admin");
                System.out.println("Password: 12345");
                System.out.println("======================================================");
            }
        }
    }
}
