package com.proof.service;

import com.proof.model.Inscripcion;
import com.proof.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    // Listar todas las inscripciones
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepository.findAll();
    }

    // Obtener una inscripción por ID
    public Optional<Inscripcion> obtenerInscripcionPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    // Guardar una inscripción
    public Inscripcion guardarInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    // Actualizar una inscripción existente
    public Inscripcion actualizarInscripcion(Long id, Inscripcion inscripcionActualizada) {
        return inscripcionRepository.findById(id)
                .map(inscripcion -> {
                    inscripcion.setId_estudiante(inscripcionActualizada.getId_estudiante());
                    inscripcion.setId_curso(inscripcionActualizada.getId_curso());
                    inscripcion.setFecha_inscripcion(inscripcionActualizada.getFecha_inscripcion());
                    return inscripcionRepository.save(inscripcion);
                }).orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + id));
    }

    // Eliminar una inscripción
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("Inscripción no encontrada con ID: " + id);
        }
        inscripcionRepository.deleteById(id);
    }
}
