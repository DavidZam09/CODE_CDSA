package com.proof.service;

import com.proof.model.Inscripcion;
import com.proof.repository.InscripcionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InscripcionServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionService inscripcionService;

    private Inscripcion inscripcion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inscripcion = new Inscripcion(1L, 10L, 20L, LocalDate.now());
    }

    @Test
    void listarInscripciones_DeberiaRetornarLista() {
        when(inscripcionRepository.findAll()).thenReturn(Arrays.asList(inscripcion));

        List<Inscripcion> resultado = inscripcionService.listarInscripciones();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(inscripcionRepository, times(1)).findAll();
    }

    @Test
    void obtenerInscripcionPorId_DeberiaRetornarInscripcion() {
        when(inscripcionRepository.findById(1L)).thenReturn(Optional.of(inscripcion));

        Optional<Inscripcion> resultado = inscripcionService.obtenerInscripcionPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId_inscripcion());
        verify(inscripcionRepository, times(1)).findById(1L);
    }

    @Test
    void guardarInscripcion_DeberiaGuardarYRetornarInscripcion() {
        when(inscripcionRepository.save(any(Inscripcion.class))).thenReturn(inscripcion);

        Inscripcion resultado = inscripcionService.guardarInscripcion(inscripcion);

        assertNotNull(resultado);
        assertEquals(inscripcion.getId_inscripcion(), resultado.getId_inscripcion());
        verify(inscripcionRepository, times(1)).save(inscripcion);
    }

    @Test
    void actualizarInscripcion_DeberiaActualizarYRetornarInscripcion() {
        Inscripcion nuevaInscripcion = new Inscripcion(1L, 30L, 40L, LocalDate.now().plusDays(1));

        when(inscripcionRepository.findById(1L)).thenReturn(Optional.of(inscripcion));
        when(inscripcionRepository.save(any(Inscripcion.class))).thenReturn(nuevaInscripcion);

        Inscripcion resultado = inscripcionService.actualizarInscripcion(1L, nuevaInscripcion);

        assertEquals(nuevaInscripcion.getId_estudiante(), resultado.getId_estudiante());
        assertEquals(nuevaInscripcion.getId_curso(), resultado.getId_curso());
        verify(inscripcionRepository, times(1)).findById(1L);
        verify(inscripcionRepository, times(1)).save(any(Inscripcion.class));
    }

    @Test
    void actualizarInscripcion_CuandoNoExiste_DeberiaLanzarExcepcion() {
        when(inscripcionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> inscripcionService.actualizarInscripcion(1L, inscripcion));
        verify(inscripcionRepository, times(1)).findById(1L);
    }

    @Test
    void eliminarInscripcion_DeberiaEliminarSiExiste() {
        when(inscripcionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(inscripcionRepository).deleteById(1L);

        assertDoesNotThrow(() -> inscripcionService.eliminarInscripcion(1L));
        verify(inscripcionRepository, times(1)).deleteById(1L);
    }

    @Test
    void eliminarInscripcion_CuandoNoExiste_DeberiaLanzarExcepcion() {
        when(inscripcionRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> inscripcionService.eliminarInscripcion(1L));
        verify(inscripcionRepository, times(1)).existsById(1L);
    }
}
