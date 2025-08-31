// src/main/java/com/inclusive/authservice/config/SecurityConfig.java
package com.inclusive.authservice.config;

import com.inclusive.authservice.security.JwtAuthEntryPoint;
import com.inclusive.authservice.security.JwtAuthenticationFilter;
import com.inclusive.authservice.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthEntryPoint authEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder; // Inyectado desde PasswordEncoderConfig

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Habilitar CORS (usa tu GlobalCorsConfig si existe) y desactivar CSRF para API REST
                .cors().and()
                .csrf().disable()

                // Manejo de excepciones por autenticación
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()

                // Stateless session (usamos JWT)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // Reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos (login/register, health, docs, h2-console si usas)
                        .requestMatchers("/api/auth/**", "/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        // Puedes permitir open access a roles si lo deseas:
                        .requestMatchers("/api/roles/**").permitAll()
                        // Rutas administrativas
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // El resto requiere autenticación
                        .anyRequest().authenticated()
                )

                // Configurar provider y filtros
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;

        // Si usas H2 Console en desarrollo:
        http.headers().frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    // AuthenticationManager expuesto para poder inyectarlo en servicios (p. ej. AuthService)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}










