package com.proof.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.proof.repository.UserRepository;
import lombok.RequiredArgsConstructor;

/**
 * Clase de configuración de la aplicación que define los beans necesarios
 * para la autenticación de usuarios.
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Application Configuration", description = "Configuración de beans para autenticación de usuarios")
@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final UserRepository userRepository;

    /**
     * Método que crea un bean de tipo AuthenticationManager.
     * 
     * @param config Configuración de autenticación.
     * @return AuthenticationManager.
     * @throws Exception
     */
    @Operation(summary = "Crea un bean de tipo AuthenticationManager", description = "Devuelve un AuthenticationManager basado en la configuración de autenticación")
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Método que crea un bean de tipo AuthenticationProvider.
     * 
     * @return AuthenticationProvider.
     */
    @Operation(summary = "Crea un bean de tipo AuthenticationProvider", description = "Devuelve un AuthenticationProvider configurado con UserDetailsService y PasswordEncoder")
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Método que crea un bean de tipo PasswordEncoder.
     * 
     * @return PasswordEncoder.
     */
    @Operation(summary = "Crea un bean de tipo PasswordEncoder", description = "Devuelve un PasswordEncoder basado en BCrypt")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Método que crea un bean de tipo UserDetailsService.
     * 
     * @return UserDetailsService.
     */
    @Operation(summary = "Crea un bean de tipo UserDetailsService", description = "Devuelve un UserDetailsService que busca usuarios por nombre de usuario")
    @Bean
    public UserDetailsService userDetailService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not fournd"));
    }
}
