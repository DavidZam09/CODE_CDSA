package com.proof.controller;

import com.proof.model.Estudiante;
import com.proof.service.EstudianteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteControllerTest {

    @Mock
    private EstudianteService estudianteService;

    @InjectMocks
    private EstudianteController estudianteController;

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiante();
        // estudiante.setId(1L);
        estudiante.setNombre("Juan Perez");
    }

    @Test
    void listarEstudiantes_DeberiaRetornarListaDeEstudiantes() {
        List<Estudiante> estudiantes = Arrays.asList(estudiante);
        when(estudianteService.listarEstudiantes()).thenReturn(estudiantes);

        ResponseEntity<?> response = estudianteController.listarEstudiantes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiantes, response.getBody());
    }

    @Test
    void listarEstudiantes_DeberiaRetornarNotFoundSiNoHayEstudiantes() {
        when(estudianteService.listarEstudiantes()).thenReturn(List.of());

        ResponseEntity<?> response = estudianteController.listarEstudiantes();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontraron estudiantes", response.getBody());
    }

    @Test
    void obtenerEstudiantePorId_DeberiaRetornarEstudiante() {
        when(estudianteService.obtenerEstudiantePorId(1L)).thenReturn(Optional.of(estudiante));

        ResponseEntity<?> response = estudianteController.obtenerEstudiantePorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Optional.of(estudiante), response.getBody());
    }

    @Test
    void obtenerEstudiantePorId_DeberiaRetornarNotFoundSiNoExiste() {
        when(estudianteService.obtenerEstudiantePorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = estudianteController.obtenerEstudiantePorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró el estudiante", response.getBody());
    }

    @Test
    void guardarEstudiante_DeberiaRetornarEstudianteGuardado() {
        when(estudianteService.guardarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        ResponseEntity<Estudiante> response = estudianteController.guardarEstudiante(estudiante);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
    }

    @Test
    void actualizarEstudiante_DeberiaRetornarEstudianteActualizado() {
        when(estudianteService.actualizarEstudiante(eq(1L), any(Estudiante.class))).thenReturn(estudiante);

        ResponseEntity<?> response = estudianteController.actualizarEstudiante(1L, estudiante);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
    }

    @Test
    void actualizarEstudiante_DeberiaRetornarNotFoundSiNoExiste() {
        when(estudianteService.actualizarEstudiante(eq(1L), any(Estudiante.class))).thenReturn(null);

        ResponseEntity<?> response = estudianteController.actualizarEstudiante(1L, estudiante);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontro al estudiante", response.getBody());
    }

    @Test
    void eliminarEstudiante_DeberiaRetornarNoContent() {
        when(estudianteService.eliminarEstudiante(1L)).thenReturn(true);

        ResponseEntity<Void> response = estudianteController.eliminarEstudiante(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void eliminarEstudiante_DeberiaRetornarNotFoundSiNoExiste() {
        when(estudianteService.eliminarEstudiante(1L)).thenReturn(false);

        ResponseEntity<Void> response = estudianteController.eliminarEstudiante(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}