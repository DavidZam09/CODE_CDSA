package com.proof.controller;

import com.proof.model.Curso;
import com.proof.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * CursoController es la clase que se encarga de manejar las peticiones HTTP
 * relacionadas con los cursos.
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Cursos", description = "API para la gestión de cursos")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    /**
     * Método que se encarga de listar todos los cursos
     * 
     * @return ResponseEntity con la lista de cursos
     */
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    @GetMapping
    public ResponseEntity<?> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        if (cursos.isEmpty()) {
            return new ResponseEntity<>("No se obtuvieron cursos", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(cursos);
        }
    }

    /**
     * Método que se encarga de obtener un curso por ID
     *
     * @param id ID del curso a obtener
     * @return ResponseEntity con el curso obtenido
     */
    @Operation(summary = "Obtener un curso por ID", description = "Obtiene un curso específico basado en su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCursoPorId(@PathVariable("id") Long id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
        if (curso.isPresent()) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el curso", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método que se encarga de crear un nuevo curso
     * 
     * @param curso Curso a crear
     * 
     * @return ResponseEntity con el curso creado
     */
    @Operation(summary = "Crear un nuevo curso", description = "Crea un nuevo curso con la información proporcionada")
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    /**
     * Método que se encarga de actualizar un curso existente
     * 
     * @param id    ID del curso a actualizar
     * @param curso Curso con los datos actualizados
     * @return ResponseEntity con el curso actualizado
     */
    @Operation(summary = "Actualizar un curso", description = "Actualiza un curso existente basado en su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        try {
            Curso cursoActualizado = cursoService.actualizarCurso(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método que se encarga de eliminar un curso
     * 
     * @param id ID del curso a eliminar
     * @return ResponseEntity con el resultado de la operación
     */
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso existente basado en su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable("id") Long id) {
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
