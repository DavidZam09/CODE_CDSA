package com.proof.controller;

import com.proof.model.Profesor;
import com.proof.service.ProfesorService;

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
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    /**
     * Método que se encarga de listar todos los profesores
     * 
     * @return ResponseEntity con la lista de profesores
     */
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
    @PutMapping("/{id}")
    public Profesor actualizarProfesor(@PathVariable("id") Long id, @Valid @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor);
    }

    /**
     * Método que se encarga de eliminar un profesor
     * 
     * @param id ID del profesor a eliminar
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProfesor(@PathVariable("id") Long id) {
        profesorService.eliminarProfesor(id);
    }
}
