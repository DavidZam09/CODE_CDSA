package com.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthResponse clase que contiene el token de autenticación
 * que se envía al cliente después de un inicio de sesión exitoso.
 * 
 * @autor David Orlando Velez Zamora
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    /**
     * token de autenticación
     */
    String token;
}
