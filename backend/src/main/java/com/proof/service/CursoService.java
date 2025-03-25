package com.proof.service;

import com.proof.model.Curso;
import com.proof.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Curso
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "CursoService", description = "Servicio para la gestión de cursos")
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Listar todos los cursos
     * 
     * @return Lista de cursos
     */
    @Operation(summary = "Listar todos los cursos", description = "Obtiene una lista de todos los cursos disponibles")
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    /**
     * Obtener un curso por ID
     * 
     * @param id ID del curso a obtener
     * @return Curso
     */
    @Operation(summary = "Obtener un curso por ID", description = "Obtiene los detalles de un curso específico por su ID")
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    /**
     * Guardar un curso
     * 
     * @param curso Curso a guardar
     * @return Curso
     */
    @Operation(summary = "Guardar un curso", description = "Guarda un nuevo curso en el sistema")
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Actualizar un curso
     * 
     * @param id               ID del curso a actualizar
     * @param cursoActualizado Curso actualizado
     * @return Curso
     */
    @Operation(summary = "Actualizar un curso", description = "Actualiza los detalles de un curso existente por su ID")
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

    /**
     * Eliminar un curso
     * 
     * @param id ID del curso a eliminar
     */
    @Operation(summary = "Eliminar un curso", description = "Elimina un curso existente por su ID")
    public void eliminarCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
}
