package com.proof.service;

import com.proof.model.Profesor;
import com.proof.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    private Profesor profesor1;
    private Profesor profesor2;

    @BeforeEach
    void setUp() {
        profesor1 = new Profesor();
        profesor1.setId_Persona(1L);
        profesor1.setEspecialidad("Matemáticas");
        profesor1.setFechaContratacion(LocalDate.of(2020, 1, 15));

        profesor2 = new Profesor();
        profesor2.setId_Persona(2L);
        profesor2.setEspecialidad("Física");
        profesor2.setFechaContratacion(LocalDate.of(2021, 6, 10));
    }

    @Test
    void listarProfesores_DeberiaRetornarListaDeProfesores() {
        List<Profesor> listaProfesores = Arrays.asList(profesor1, profesor2);
        when(profesorRepository.findAll()).thenReturn(listaProfesores);

        List<Profesor> resultado = profesorService.listarProfesores();

        assertEquals(2, resultado.size());
        verify(profesorRepository, times(1)).findAll();
    }

    @Test
    void obtenerProfesorPorId_ProfesorExiste_DeberiaRetornarProfesor() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor1));

        Optional<Profesor> resultado = profesorService.obtenerProfesorPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matemáticas", resultado.get().getEspecialidad());
        verify(profesorRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerProfesorPorId_ProfesorNoExiste_DeberiaRetornarEmpty() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Profesor> resultado = profesorService.obtenerProfesorPorId(1L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void guardarProfesor_DeberiaGuardarYRetornarProfesor() {
        when(profesorRepository.save(profesor1)).thenReturn(profesor1);

        Profesor resultado = profesorService.guardarProfesor(profesor1);

        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getEspecialidad());
        verify(profesorRepository, times(1)).save(profesor1);
    }

    @Test
    void actualizarProfesor_DeberiaActualizarYRetornarProfesor() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor1));
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor1);

        Profesor profesorActualizado = new Profesor();
        profesorActualizado.setEspecialidad("Ciencias");
        profesorActualizado.setFechaContratacion(LocalDate.of(2019, 5, 20));

        Profesor resultado = profesorService.actualizarProfesor(1L, profesorActualizado);

        assertNotNull(resultado);
        assertEquals("Ciencias", resultado.getEspecialidad());
        assertEquals(LocalDate.of(2019, 5, 20), resultado.getFechaContratacion());
        verify(profesorRepository, times(1)).findById(1L);
        verify(profesorRepository, times(1)).save(any(Profesor.class));
    }

    @Test
    void actualizarProfesor_ProfesorNoEncontrado_DeberiaLanzarExcepcion() {
        when(profesorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            profesorService.actualizarProfesor(1L, profesor1);
        });

        assertEquals("Profesor no encontrado con ID: 1", exception.getMessage());
    }

    @Test
    void eliminarProfesor_ProfesorExiste_DeberiaEliminarProfesor() {
        when(profesorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(profesorRepository).deleteById(1L);

        profesorService.eliminarProfesor(1L);

        verify(profesorRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarProfesor_ProfesorNoExiste_DeberiaLanzarExcepcion() {
        when(profesorRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            profesorService.eliminarProfesor(1L);
        });

        assertEquals("Profesor no encontrado con ID: 1", exception.getMessage());
    }
}
