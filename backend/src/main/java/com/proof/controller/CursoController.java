package com.proof.controller;

import com.proof.model.Curso;
import com.proof.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    // Obtener un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable("id") Long id) {
        Optional<Curso> curso = cursoService.obtenerCursoPorId(id);
        return curso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.guardarCurso(curso);
        return ResponseEntity.ok(nuevoCurso);
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable("id") Long id, @RequestBody Curso curso) {
        try {
            Curso cursoActualizado = cursoService.actualizarCurso(id, curso);
            return ResponseEntity.ok(cursoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
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
