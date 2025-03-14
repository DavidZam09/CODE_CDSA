package com.proof.service;

import com.proof.exception.RecursoNoEncontradoException;
import com.proof.model.Estudiante;
import com.proof.model.Persona;
import com.proof.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Estudiante();
        persona.setId_Persona(1L);
        persona.setNombre("Juan");
        persona.setApellido("Pérez");
        persona.setEmail("juan@example.com");
        persona.setTelefono("1234567890");
    }

    @Test
    void testGuardarPersona() {
        when(personaRepository.save(persona)).thenReturn(persona);

        Persona personaGuardada = personaService.guardarPersona(persona);

        assertNotNull(personaGuardada);
        assertEquals(persona.getId_Persona(), personaGuardada.getId_Persona());
        assertEquals("Juan", personaGuardada.getNombre());
        verify(personaRepository, times(1)).save(persona);
    }

    @Test
    void testListarPersonas() {
        List<Persona> lista = Arrays.asList(persona, new Estudiante()); // Usar subclase concreta

        when(personaRepository.findAll()).thenReturn(lista);

        List<Persona> resultado = personaService.listarPersonas();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(personaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPersonaPorId_Encontrada() {
        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));

        Optional<Persona> personaEncontrada = personaService.obtenerPersonaPorId(1L);

        assertTrue(personaEncontrada.isPresent());
        assertEquals(persona.getId_Persona(), personaEncontrada.get().getId_Persona());
        verify(personaRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPersonaPorId_NoEncontrada() {
        assertThrows(RecursoNoEncontradoException.class, () -> {
            personaService.obtenerPersonaPorId(2L);
        });
    }

    @Test
    void testEliminarPersona() {
        doNothing().when(personaRepository).deleteById(1L);

        personaService.eliminarPersona(1L);

        verify(personaRepository, times(1)).deleteById(1L);
    }
}
