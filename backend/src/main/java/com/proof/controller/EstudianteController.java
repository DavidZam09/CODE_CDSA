package com.proof.controller;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * EstudianteController class contienen los métodos para gestionar los
 * estudiantes
 * 
 * @autor David Orlando Velez Zamora
 */

@Tag(name = "Estudiantes", description = "API para la gestión de estudiantes")
@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * Método que se encarga de listar todos los estudiantes
     * 
     * @return ResponseEntity con la lista de estudiantes
     */
    @Operation(summary = "Listar todos los estudiantes", description = "Obtiene una lista de todos los estudiantes registrados")
    @GetMapping
    public ResponseEntity<?> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
        if (estudiantes.isEmpty()) {
            return new ResponseEntity<>("No se encontraron estudiantes", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estudiantes, HttpStatus.OK);
        }
    }

    /**
     * Método que se encarga de obtener un estudiante por ID
     * 
     * @param id ID del estudiante a obtener
     * @return ResponseEntity con el estudiante obtenido
     */
    @Operation(summary = "Obtener un estudiante por ID", description = "Obtiene un estudiante específico mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEstudiantePorId(@PathVariable("id") Long id) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorId(id);
        if (estudiante.isPresent()) {
            return new ResponseEntity<>(estudiante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método que se encarga de guardar un nuevo estudiante
     * 
     * @param estudiante Estudiante a guardar
     * @return ResponseEntity con el estudiante guardado
     */
    @Operation(summary = "Guardar un nuevo estudiante", description = "Crea un nuevo estudiante con los datos proporcionados")
    @PostMapping
    public ResponseEntity<Estudiante> guardarEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante savedEstudiante = estudianteService.guardarEstudiante(estudiante);
        return new ResponseEntity<>(savedEstudiante, HttpStatus.CREATED);
    }

    /**
     * Método que se encarga de actualizar un estudiante existente
     * 
     * @param id         ID del estudiante a actualizar
     * @param estudiante Estudiante con los datos actualizados
     * @return ResponseEntity con el estudiante actualizado
     */
    @Operation(summary = "Actualizar un estudiante existente", description = "Actualiza los datos de un estudiante existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEstudiante(@PathVariable("id") Long id,
            @RequestBody Estudiante estudiante) {
        Estudiante updatedEstudiante = estudianteService.actualizarEstudiante(id, estudiante);
        if (updatedEstudiante != null) {
            return new ResponseEntity<>(updatedEstudiante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontro al estudiante", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método que se encarga de eliminar un estudiante
     * 
     * @param id ID del estudiante a eliminar
     * @return ResponseEntity con el resultado de la eliminación
     */
    @Operation(summary = "Eliminar un estudiante", description = "Elimina un estudiante específico mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable("id") Long id) {
        boolean isRemoved = estudianteService.eliminarEstudiante(id);
        if (isRemoved) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
