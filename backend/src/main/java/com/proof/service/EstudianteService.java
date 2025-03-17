package com.proof.service;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import com.proof.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNumeroMatricula(estudianteActualizado.getNumeroMatricula());
                    estudiante.setGrado(estudianteActualizado.getGrado());
                    return estudianteRepository.save(estudiante);
                }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

}
