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

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    // Listar todos los profesores
    @GetMapping
    public List<Profesor> listarProfesores() {
        return profesorService.listarProfesores();
    }

    // Obtener un profesor por ID
    @GetMapping("/{id}")
    public Optional<Profesor> obtenerProfesorPorId(@PathVariable Long id) {
        return profesorService.obtenerProfesorPorId(id);
    }

    // Agregar un nuevo profesor
    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@Valid @RequestBody Profesor profesor) {
        return ResponseEntity.ok(profesorService.guardarProfesor(profesor));
    }

    // Actualizar un profesor existente
    @PutMapping("/{id}")
    public Profesor actualizarProfesor(@PathVariable Long id, @Valid @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor);
    }

    // Eliminar un profesor
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProfesor(@PathVariable Long id) {
        profesorService.eliminarProfesor(id);
    }
}
