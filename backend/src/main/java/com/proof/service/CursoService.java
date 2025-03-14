package com.proof.service;

import com.proof.model.Curso;
import com.proof.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    // Obtener un curso por ID
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Guardar un curso
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Actualizar un curso
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id)
            .map(curso -> {
                curso.setNombre(cursoActualizado.getNombre());
                curso.setDescripcion(cursoActualizado.getDescripcion());
                curso.setCreditos(cursoActualizado.getCreditos());
                curso.setIdProfesor(cursoActualizado.getIdProfesor());
                return cursoRepository.save(curso);
            }).orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    // Eliminar un curso
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
