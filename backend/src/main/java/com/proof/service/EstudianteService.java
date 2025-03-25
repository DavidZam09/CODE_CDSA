package com.proof.service;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import com.proof.repository.EstudianteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Estudiante
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "EstudianteService", description = "Servicio para la gestión de estudiantes")
@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    /**
     * Listar todos los estudiantes
     * 
     * @return Lista de estudiantes
     */
    @Operation(summary = "Listar todos los estudiantes", description = "Obtiene una lista de todos los estudiantes registrados")
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    /**
     * Obtener un estudiante por ID
     * 
     * @param id ID del estudiante a obtener
     * @return Estudiante
     */
    @Operation(summary = "Obtener un estudiante por ID", description = "Obtiene un estudiante específico mediante su ID")
    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    /**
     * Guardar un estudiante
     * 
     * @param estudiante Estudiante a guardar
     * @return Estudiante
     */
    @Operation(summary = "Guardar un estudiante", description = "Guarda un nuevo estudiante en el sistema")
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
    @Operation(summary = "Actualizar un estudiante", description = "Actualiza los datos de un estudiante existente")
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
    @Operation(summary = "Eliminar un estudiante", description = "Elimina un estudiante del sistema mediante su ID")
    public boolean eliminarEstudiante(Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
