package com.proof.controller;

import com.proof.model.Profesor;
import com.proof.service.ProfesorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ProfesorController Class controlador de Profesor
 * ProfesorController es la clase que se encarga de manejar las peticiones HTTP
 * 
 * @autor David Orlando Velez Zamora
 */
@RestController
@RequestMapping("/api/v1/profesores")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Profesores", description = "API para la gestión de profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    /**
     * Método que se encarga de listar todos los profesores
     * 
     * @return ResponseEntity con la lista de profesores
     */
    @Operation(summary = "Listar todos los profesores", description = "Obtiene una lista de todos los profesores registrados")
    @GetMapping
    public ResponseEntity<?> listarProfesores() {
        List<Profesor> profesores = profesorService.listarProfesores();
        if (profesores.isEmpty()) {
            return new ResponseEntity<>("No se encontraron profesores", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(profesores, HttpStatus.OK);
        }
    }

    /**
     * Método que se encarga de obtener un profesor por ID
     * 
     * @param id ID del profesor a obtener
     * @return ResponseEntity con el profesor obtenido
     */
    @Operation(summary = "Obtener un profesor por ID", description = "Obtiene los detalles de un profesor específico mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable("id") Long id) {
        Optional<Profesor> profesor = profesorService.obtenerProfesorPorId(id);
        if (profesor.isEmpty()) {
            return new ResponseEntity<>("No se obtuvo el profesor", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(profesor);
        }
    }

    /**
     * Método que se encarga de crear un nuevo profesor
     * 
     * @param profesor Profesor a guardar
     * @return ResponseEntity con el profesor guardado
     */
    @Operation(summary = "Crear un nuevo profesor", description = "Crea un nuevo profesor en el sistema")
    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor) {
        return ResponseEntity.ok(profesorService.guardarProfesor(profesor));
    }

    /**
     * Método que se encarga de actualizar un profesor
     * 
     * @param id       ID del profesor a actualizar
     * @param profesor Profesor a actualizar
     * @return Profesor actualizado
     */
    @Operation(summary = "Actualizar un profesor", description = "Actualiza los datos de un profesor existente")
    @PutMapping("/{id}")
    public Profesor actualizarProfesor(@PathVariable("id") Long id, @Valid @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor);
    }

    /**
     * Método que se encarga de eliminar un profesor
     * 
     * @param id ID del profesor a eliminar
     */
    @Operation(summary = "Eliminar un profesor", description = "Elimina un profesor del sistema mediante su ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProfesor(@PathVariable("id") Long id) {
        profesorService.eliminarProfesor(id);
    }
}
