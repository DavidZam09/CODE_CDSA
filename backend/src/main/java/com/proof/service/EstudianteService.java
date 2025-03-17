package com.proof.service;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import com.proof.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Estudiante
 * 
 * @autor David Orlando Velez Zamora
 */
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * Listar todos los estudiantes
     * 
     * @return Lista de estudiantes
     */
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    /**
     * Obtener un estudiante por ID
     * 
     * @param id ID del estudiante a obtener
     * @return Estudiante
     */
    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    /**
     * Guardar un estudiante
     * 
     * @param estudiante Estudiante a guardar
     * @return Estudiante
     */
    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    /**
     * Actualizar un estudiante
     * 
     * @param id                    ID del estudiante a actualizar
     * @param estudianteActualizado Estudiante actualizado
     * @return Estudiante
     */
    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNumeroMatricula(estudianteActualizado.getNumeroMatricula());
                    estudiante.setGrado(estudianteActualizado.getGrado());
                    return estudianteRepository.save(estudiante);
                }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    /**
     * Eliminar un estudiante
     * 
     * @param id ID del estudiante a eliminar
     * @return boolean
     */
    public boolean eliminarEstudiante(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
