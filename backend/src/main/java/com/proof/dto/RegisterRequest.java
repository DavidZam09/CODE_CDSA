package com.proof.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisterRequest clase que contiene los datos de registro
 * que se envían al servidor para crear un nuevo usuario.
 * 
 * @autor David Orlando Velez Zamora
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Schema(description = "Nombre de usuario único", example = "johndoe123")
    String username;

    @Schema(description = "Contraseña del usuario", example = "password123")
    String password;

    @Schema(description = "Primer nombre del usuario", example = "John")
    String firstname;

    @Schema(description = "Apellido del usuario", example = "Doe")
    String lastname;

    @Schema(description = "País de residencia del usuario", example = "Colombia")
    String country;
}