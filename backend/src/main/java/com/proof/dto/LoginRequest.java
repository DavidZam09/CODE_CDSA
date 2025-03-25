package com.proof.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginRequest clase que contiene los datos de inicio de sesión
 * que se envían al servidor para autenticar a un usuario.
 * 
 * @autor David Orlando Velez Zamora
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(description = "Nombre de usuario para iniciar sesión", example = "usuario123")
    String username;

    @Schema(description = "Contraseña del usuario", example = "password123")
    String password;
}
