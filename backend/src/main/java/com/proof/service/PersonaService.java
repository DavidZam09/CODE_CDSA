package com.proof.service;

import com.proof.model.Persona;
import com.proof.repository.PersonaRepository;
import com.proof.exception.RecursoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Persona
 * 
 * @autor David Orlando Velez Zamora
 */
@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Listar todas las personas
     * 
     * @return Lista de personas
     */
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    /**
     * Obtener una persona por ID
     * 
     * @param id ID de la persona a obtener
     * @return Persona
     */
    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id)
                .or(() -> {
                    throw new RecursoNoEncontradoException("No se encontró la persona con ID: " + id);
                });
    }

    /**
     * Guardar una persona
     * 
     * @param persona Persona a guardar
     * @return Persona
     */
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    /**
     * Eliminar una persona
     * 
     * @param id ID de la persona a eliminar
     */
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }

    /**
     * Actualizar una persona
     * 
     * @param id                 ID de la persona a actualizar
     * @param personaActualizada Persona actualizada
     * @return Persona
     */
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
