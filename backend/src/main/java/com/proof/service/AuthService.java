package com.proof.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proof.dto.AuthResponse;
import com.proof.dto.LoginRequest;
import com.proof.dto.RegisterRequest;
import com.proof.model.Role;
import com.proof.model.User;
import com.proof.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de autenticación
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Auth Service", description = "Servicio para autenticación y registro de usuarios")
@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        /**
         * Método para autenticar un usuario
         * 
         * @param request
         * @return
         */
        @Operation(summary = "Autenticar usuario", description = "Autentica un usuario existente y devuelve un token JWT")
        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(user);
                return AuthResponse.builder()
                                .token(token)
                                .build();

        }

        /**
         * Método para registrar un usuario
         * 
         * @param request
         * @return
         */
        @Operation(summary = "Registrar usuario", description = "Registra un nuevo usuario y devuelve un token JWT")
        public AuthResponse register(RegisterRequest request) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .country(request.getCountry())
                                .role(Role.USER)
                                .build();

                userRepository.save(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();

        }

}