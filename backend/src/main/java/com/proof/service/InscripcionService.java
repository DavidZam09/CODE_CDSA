package com.proof.service;

import com.proof.model.Inscripcion;
import com.proof.repository.InscripcionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Inscripcion
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "InscripcionService", description = "Servicio para la gestión de inscripciones")
@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    /**
     * Listar todas las inscripciones
     * 
     * @return Lista de inscripciones
     */
    @Operation(summary = "Listar inscripciones", description = "Obtiene una lista de todas las inscripciones")
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    /**
     * Obtener una inscripción por ID
     * 
     * @param id ID de la inscripción a obtener
     * @return Inscripción
     */
    @Operation(summary = "Obtener inscripción por ID", description = "Obtiene una inscripción específica por su ID")
    public Optional<Inscripcion> obtenerInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    /**
     * Guardar una inscripción
     * 
     * @param inscripcion Inscripción a guardar
     * @return Inscripción
     */
    @Operation(summary = "Guardar inscripción", description = "Guarda una nueva inscripción en la base de datos")
    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    /**
     * Actualizar una inscripción
     * 
     * @param id                     ID de la inscripción a actualizar
     * @param inscripcionActualizada Inscripción actualizada
     * @return Inscripción
     */
    @Operation(summary = "Actualizar inscripción", description = "Actualiza una inscripción existente por su ID")
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcionActualizada) {
        return inscripcionRepository.findById(id)
                .map(inscripcion -> {
                    inscripcion.setId_estudiante(inscripcionActualizada.getId_estudiante());
                    inscripcion.setId_curso(inscripcionActualizada.getId_curso());
                    inscripcion.setFecha_inscripcion(inscripcionActualizada.getFecha_inscripcion());
                    return inscripcionRepository.save(inscripcion);
                }).orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + id));
    }

    /**
     * Eliminar una inscripción
     * 
     * @param id ID de la inscripción a eliminar
     */
    @Operation(summary = "Eliminar inscripción", description = "Elimina una inscripción existente por su ID")
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada con ID: " + id);
        }
        inscripcionRepository.deleteById(id);
    }
}
