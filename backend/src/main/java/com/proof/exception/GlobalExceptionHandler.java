package com.proof.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase GlobalExceptionHandler que maneja las excepciones de la aplicación
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Global Exception Handler", description = "Manejador global de excepciones de la aplicación")
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Método que maneja la excepción RecursoNoEncontradoException
     * 
     * @param ex Excepción RecursoNoEncontradoException
     * @return ResponseEntity con el mensaje de la excepción
     */
    @Operation(summary = "Manejar RecursoNoEncontradoException", description = "Maneja excepciones cuando un recurso no es encontrado")
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Método que maneja la excepción RecursoDuplicadoException
     * 
     * @param ex Excepción RecursoDuplicadoException
     * @return ResponseEntity con el mensaje de la excepción
     */
    @Operation(summary = "Manejar MethodArgumentNotValidException", description = "Maneja excepciones de validación de argumentos")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
