package com.proof.exception;

/**
 * RecursoNoEncontradoException clase que maneja las excepciones
 * cuando un recurso no se encuentra en la base de datos.
 * 
 * @autor David Orlando Velez Zamora
 */
public class RecursoNoEncontradoException extends RuntimeException {
    /**
     * Constructor de la clase RecursoNoEncontradoException
     * 
     * @param mensaje Mensaje de la excepción
     */
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
