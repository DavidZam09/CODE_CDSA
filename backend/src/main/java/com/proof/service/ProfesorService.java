package com.proof.service;

import com.proof.model.Profesor;
import com.proof.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Profesor
 * 
 * @autor David Orlando Velez Zamora
 */
@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    /**
     * Listar todos los profesores
     * 
     * @return Lista de profesores
     */
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    /**
     * Obtener un profesor por ID
     * 
     * @param id ID del profesor a obtener
     * @return Profesor
     */
    public Optional<Profesor> obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id);
    }

    /**
     * Guardar un profesor
     * 
     * @param profesor Profesor a guardar
     * @return Profesor
     */
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
    public void eliminarProfesor(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor no encontrado con ID: " + id);
        }
        profesorRepository.deleteById(id);
    }
}
