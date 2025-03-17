package com.proof.controller;

import com.proof.model.Inscripcion;
import com.proof.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    // Obtener todas las inscripciones
    @GetMapping
    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.listarInscripciones();
    }

    // Obtener una inscripción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcionPorId(@PathVariable("id") Long id) {
        Optional<Inscripcion> inscripcion = inscripcionService.obtenerInscripcionPorId(id);
        return inscripcion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva inscripción
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        Inscripcion nuevaInscripcion = inscripcionService.guardarInscripcion(inscripcion);
        return ResponseEntity.ok(nuevaInscripcion);
    }

    // Actualizar una inscripción existente
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable("id") Long id,
            @RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion inscripcionActualizada = inscripcionService.actualizarInscripcion(id, inscripcion);
            return ResponseEntity.ok(inscripcionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable("id") Long id) {
        try {
            inscripcionService.eliminarInscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
