package com.msd.atlantis_fest.config;

import com.msd.atlantis_fest.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // ENDPOINTS PÚBLICOS
                        .requestMatchers("/authorizations/**", "/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET, 
                                "/artistas/**", 
                                "/conciertos/**", 
                                "/festivales/**", 
                                "/foodtrucks/**", 
                                "/generos/**", 
                                "/zonas/**",
                                "/tipos-ticket/**",
                                "/reviews/**"
                        ).permitAll()

                        // ENDPOINTS DE CLIENTE
                        .requestMatchers("/compras/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST, "/reviews").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT, "/reviews/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasRole("CLIENT")

                        // ENDPOINTS DE ARTISTA (para su propio perfil)
                        .requestMatchers(HttpMethod.PUT, "/artistas/{id}").hasAnyRole("ARTIST", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/artistas/{id}/foto").hasAnyRole("ARTIST", "ADMIN")

                        // ENDPOINTS DE FOODTRUCK (para su propio perfil)
                        .requestMatchers(HttpMethod.PUT, "/foodtrucks/{id}").hasAnyRole("FOODTRUCK", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/foodtrucks/{id}/foto", "/foodtrucks/{id}/menu").hasAnyRole("FOODTRUCK", "ADMIN")
                        
                        // CUALQUIER OTRA COSA REQUIERE SER ADMIN
                        .anyRequest().hasRole("ADMIN")
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Cache-Control"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
