package com.proof.service;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import com.proof.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        if (estudiantes.isEmpty()) {
            throw new RuntimeException("No hay estudiantes registrados.");
        } 
        return estudiantes;
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
