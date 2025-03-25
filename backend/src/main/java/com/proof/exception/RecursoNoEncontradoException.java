package com.proof.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * RecursoNoEncontradoException clase que maneja las excepciones
 * cuando un recurso no se encuentra en la base de datos.
 * 
 * @autor David Orlando Velez Zamora
 */
@Schema(description = "Excepción lanzada cuando un recurso no se encuentra en la base de datos.")
public class RecursoNoEncontradoException extends RuntimeException {
    /**
     * Constructor de la clase RecursoNoEncontradoException
     * 
     * @param mensaje Mensaje de la excepción
     */
    public RecursoNoEncontradoException(
        @Schema(description = "Mensaje descriptivo de la excepción.") String mensaje) {
        super(mensaje);
    }
}
