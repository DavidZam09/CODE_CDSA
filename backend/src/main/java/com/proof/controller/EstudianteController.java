package com.proof.controller;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public List<Estudiante> listarEstudiantes() {
        return estudianteService.listarEstudiantes();
    }

    @GetMapping("/{id}")
    public Optional<Estudiante> obtenerProfesorPorId(@PathVariable("id") Long id) {
        return estudianteService.obtenerEstudiantePorId(id);
    }

    @PostMapping
    public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.guardarEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public Estudiante actualizarEstudiante(@PathVariable("id") Long id, @RequestBody Estudiante estudiante) {
        return estudianteService.actualizarEstudiante(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstudiante(@PathVariable("id") Long id) {
        estudianteService.eliminarEstudiante(id);
    }
}
