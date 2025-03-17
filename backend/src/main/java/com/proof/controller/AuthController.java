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

/**
 * Controlador de autenticación que maneja las solicitudes de inicio de sesión y
 * registro de usuarios.
 * 
 * @autor David Orlando Velez Zamora
 */
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
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}