package com.proof.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proof.dto.AuthResponse;
import com.proof.dto.LoginRequest;
import com.proof.dto.RegisterRequest;
import com.proof.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador de autenticación que maneja las solicitudes de inicio de sesión y
 * registro de usuarios.
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Autenticación", description = "Controlador para la autenticación de usuarios")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    /**
     * Método que maneja las solicitudes de inicio de sesión.
     * 
     * @param request Solicitud de inicio de sesión.
     * @return ResponseEntity con la respuesta de la solicitud.
     */
    @Operation(summary = "Iniciar sesión", description = "Permite a un usuario iniciar sesión en el sistema")
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Método que maneja las solicitudes de registro de usuarios.
     * 
     * @param request Solicitud de registro de usuario.
     * @return ResponseEntity con la respuesta de la solicitud.
     */
    @Operation(summary = "Registrar usuario", description = "Permite registrar un nuevo usuario en el sistema")
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}