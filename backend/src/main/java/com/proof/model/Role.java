package com.proof.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enumeración que representa los roles de los usuarios
 * 
 * @autor David Orlando Velez Zamora
 */
@Schema(description = "Enumeración que representa los roles de los usuarios")
public enum Role {
    @Schema(description = "Rol de administrador con privilegios completos")
    ADMIN,
    @Schema(description = "Rol de usuario con privilegios limitados")
    USER
}
