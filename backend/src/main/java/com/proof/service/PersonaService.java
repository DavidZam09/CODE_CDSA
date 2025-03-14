package com.proof.service;

import com.proof.model.Persona;
import com.proof.repository.PersonaRepository;
import com.proof.exception.RecursoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id)
                .or(() -> {
                    throw new RecursoNoEncontradoException("No se encontró la persona con ID: " + id);
                });
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        return personaRepository.findById(id).map(persona -> {
            persona.setNombre(personaActualizada.getNombre());
            persona.setApellido(personaActualizada.getApellido());
            persona.setFechaNacimiento(personaActualizada.getFechaNacimiento());
            persona.setEmail(personaActualizada.getEmail());
            persona.setTelefono(personaActualizada.getTelefono());
            return personaRepository.save(persona);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }
}
