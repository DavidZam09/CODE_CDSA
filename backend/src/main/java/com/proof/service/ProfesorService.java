package com.proof.service;

import com.proof.model.Profesor;
import com.proof.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Profesor
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "ProfesorService", description = "Servicio para la gestión de profesores")
@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    /**
     * Listar todos los profesores
     * 
     * @return Lista de profesores
     */
    @Operation(summary = "Listar todos los profesores", description = "Obtiene una lista de todos los profesores registrados")
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    /**
     * Obtener un profesor por ID
     * 
     * @param id ID del profesor a obtener
     * @return Profesor
     */
    @Operation(summary = "Obtener un profesor por ID", description = "Obtiene un profesor específico basado en su ID")
    public Optional<Profesor> obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id);
    }

    /**
     * Guardar un profesor
     * 
     * @param profesor Profesor a guardar
     * @return Profesor
     */
    @Operation(summary = "Guardar un profesor", description = "Guarda un nuevo profesor en el sistema")
    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    /**
     * Actualizar un profesor
     * 
     * @param id                  ID del profesor a actualizar
     * @param profesorActualizado Profesor actualizado
     * @return Profesor
     */
    @Operation(summary = "Actualizar un profesor", description = "Actualiza los datos de un profesor existente")
    public Profesor actualizarProfesor(Long id, Profesor profesorActualizado) {
        return profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setEspecialidad(profesorActualizado.getEspecialidad());
                    profesor.setFechaContratacion(profesorActualizado.getFechaContratacion());
                    return profesorRepository.save(profesor);
                }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
    }

    /**
     * Eliminar un profesor
     * 
     * @param id ID del profesor a eliminar
     * @throws RuntimeException si no se encuentra el profesor con el ID
     *                          proporcionado
     */
    @Operation(summary = "Eliminar un profesor", description = "Elimina un profesor basado en su ID")
    public void eliminarProfesor(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor no encontrado con ID: " + id);
        }
        profesorRepository.deleteById(id);
    }
}
