package com.proof.service;

import com.proof.model.Profesor;
import com.proof.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    // Listar todos los profesores
    public List<Profesor> listarProfesores() {
        return profesorRepository.findAll();
    }

    // Buscar un profesor por ID
    public Optional<Profesor> obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id);
    }

    // Guardar un nuevo profesor
    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    // Actualizar un profesor existente
    public Profesor actualizarProfesor(Long id, Profesor profesorActualizado) {
        return profesorRepository.findById(id)
            .map(profesor -> {
                profesor.setEspecialidad(profesorActualizado.getEspecialidad());
                profesor.setFechaContratacion(profesorActualizado.getFechaContratacion());
                return profesorRepository.save(profesor);
            }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
    }

    // Eliminar un profesor
    public void eliminarProfesor(Long id) {
        if (!profesorRepository.existsById(id)) {
            throw new RuntimeException("Profesor no encontrado con ID: " + id);
        }
        profesorRepository.deleteById(id);
    }
}
