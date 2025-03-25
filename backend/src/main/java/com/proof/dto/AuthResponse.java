package com.proof.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Clase que contiene el token de autenticación enviado al cliente tras un inicio de sesión exitoso.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    /**
     * token de autenticación
     */
    @Schema(description = "Token de autenticación generado tras un inicio de sesión exitoso.")
    String token;
}
