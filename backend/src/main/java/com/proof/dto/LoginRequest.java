package com.proof.dto;

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
    String username;
    String password;
}
