package com.proof.controller;

import com.proof.model.Inscripcion;
import com.proof.service.InscripcionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InscripcionControllerTest {

    @Mock
    private InscripcionService inscripcionService;

    @InjectMocks
    private InscripcionController inscripcionController;

    private Inscripcion inscripcion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inscripcion = new Inscripcion(1L, 10L, 20L, LocalDate.now());
    }

    @Test
    void listarInscripciones_DeberiaRetornarLista() {
        when(inscripcionService.listarInscripciones()).thenReturn(Arrays.asList(inscripcion));

        List<Inscripcion> resultado = inscripcionController.listarInscripciones();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(inscripcionService, times(1)).listarInscripciones();
    }

    @Test
    void obtenerInscripcionPorId_DeberiaRetornarInscripcion() {
        when(inscripcionService.obtenerInscripcionPorId(1L)).thenReturn(Optional.of(inscripcion));

        ResponseEntity<Inscripcion> respuesta = inscripcionController.obtenerInscripcionPorId(1L);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertNotNull(respuesta.getBody());
        assertEquals(1L, respuesta.getBody().getId_inscripcion());
        verify(inscripcionService, times(1)).obtenerInscripcionPorId(1L);
    }

    @Test
    void obtenerInscripcionPorId_CuandoNoExiste_DeberiaRetornar404() {
        when(inscripcionService.obtenerInscripcionPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Inscripcion> respuesta = inscripcionController.obtenerInscripcionPorId(1L);

        assertEquals(404, respuesta.getStatusCodeValue());
        verify(inscripcionService, times(1)).obtenerInscripcionPorId(1L);
    }

    @Test
    void crearInscripcion_DeberiaGuardarYRetornarInscripcion() {
        when(inscripcionService.guardarInscripcion(any(Inscripcion.class))).thenReturn(inscripcion);

        ResponseEntity<Inscripcion> respuesta = inscripcionController.crearInscripcion(inscripcion);

        assertEquals(200, respuesta.getStatusCodeValue());
        assertNotNull(respuesta.getBody());
        assertEquals(inscripcion.getId_inscripcion(), respuesta.getBody().getId_inscripcion());
        verify(inscripcionService, times(1)).guardarInscripcion(inscripcion);
    }

    @Test
    void actualizarInscripcion_DeberiaActualizarYRetornarInscripcion() {
        when(inscripcionService.actualizarInscripcion(anyLong(), any(Inscripcion.class))).thenReturn(inscripcion);

        ResponseEntity<Inscripcion> respuesta = inscripcionController.actualizarInscripcion(1L, inscripcion);

        assertEquals(200, respuesta.getStatusCodeValue());
        verify(inscripcionService, times(1)).actualizarInscripcion(1L, inscripcion);
    }

    @Test
    void actualizarInscripcion_CuandoNoExiste_DeberiaRetornar404() {
        when(inscripcionService.actualizarInscripcion(anyLong(), any(Inscripcion.class)))
                .thenThrow(new RuntimeException("Inscripción no encontrada"));

        ResponseEntity<Inscripcion> respuesta = inscripcionController.actualizarInscripcion(1L, inscripcion);

        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    void eliminarInscripcion_DeberiaEliminarYRetornar204() {
        doNothing().when(inscripcionService).eliminarInscripcion(1L);

        ResponseEntity<Void> respuesta = inscripcionController.eliminarInscripcion(1L);

        assertEquals(204, respuesta.getStatusCodeValue());
        verify(inscripcionService, times(1)).eliminarInscripcion(1L);
    }
}
