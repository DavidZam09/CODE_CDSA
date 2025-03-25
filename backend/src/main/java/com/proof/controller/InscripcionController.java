package com.proof.controller;

import com.proof.model.Inscripcion;
import com.proof.service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * InscripcionController Class controlador de Inscripcion de estudiantes
 * 
 * @autor David Orlando Velez Zamora
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/inscripciones")
@Tag(name = "Inscripciones", description = "API para la gestión de inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    /**
     * Método que se encarga de listar todas las inscripciones
     * 
     * @return ResponseEntity con la lista de inscripciones
     */
    @Operation(summary = "Listar inscripciones", description = "Obtiene una lista de todas las inscripciones")
    @GetMapping
    public ResponseEntity<?> listarInscripciones() {
        List<Inscripcion> inscripcions = inscripcionService.listarInscripciones();
        if (inscripcions.isEmpty()) {
            return new ResponseEntity<>("No se obtuvo la inscripcion", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(inscripcions, HttpStatus.OK);
        }
    }

    /**
     * Método que se encarga de obtener una inscripcion por ID
     * 
     * @param id ID de la inscripcion a obtener
     * @return ResponseEntity con la inscripcion obtenida
     */
    @Operation(summary = "Obtener inscripcion por ID", description = "Obtiene una inscripcion específica por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInscripcionPorId(@PathVariable("id") Long id) {
        Optional<Inscripcion> inscripcion = inscripcionService.obtenerInscripcionPorId(id);
        if (inscripcion.isEmpty()) {
            return new ResponseEntity<>("No se obtuvo la inscripcion", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(inscripcion, HttpStatus.OK);
        }
    }

    /**
     * Método que se encarga de crear una nueva inscripcion
     * 
     * @param inscripcion inscripcion a guardar
     * @return ResponseEntity con la inscripcion guardada
     */
    @Operation(summary = "Crear inscripcion", description = "Crea una nueva inscripcion")
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        Inscripcion nuevaInscripcion = inscripcionService.guardarInscripcion(inscripcion);
        return ResponseEntity.ok(nuevaInscripcion);
    }

    /**
     * Método que se encarga de actualizar una inscripcion existente
     * 
     * @param id          ID de la inscripcion a actualizar
     * @param inscripcion inscripcion con los datos actualizados
     * @return ResponseEntity con la inscripcion actualizada
     */
    @Operation(summary = "Actualizar inscripcion", description = "Actualiza una inscripcion existente por su ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInscripcion(@PathVariable("id") Long id,
            @RequestBody Inscripcion inscripcion) {
        try {
            Inscripcion inscripcionActualizada = inscripcionService.actualizarInscripcion(id, inscripcion);
            return ResponseEntity.ok(inscripcionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método que se encarga de eliminar una inscripcion
     * 
     * @param id ID de la inscripcion a eliminar
     * @return ResponseEntity con la respuesta de la eliminación
     */
    @Operation(summary = "Eliminar inscripcion", description = "Elimina una inscripcion existente por su ID")
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
