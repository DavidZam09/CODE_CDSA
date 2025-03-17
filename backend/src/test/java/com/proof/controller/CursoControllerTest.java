package com.proof.controller;

import com.proof.model.Curso;
import com.proof.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class CursoControllerTest {

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCursos() {
        List<Curso> cursos = Arrays.asList(new Curso(), new Curso());
        when(cursoService.listarCursos()).thenReturn(cursos);

        ResponseEntity<?> response = cursoController.listarCursos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cursos, response.getBody());
    }

    @Test
    public void testListarCursos_NotFound() {
        when(cursoService.listarCursos()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = cursoController.listarCursos();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se obtuvieron cursos", response.getBody());
    }

    @Test
    public void testObtenerCursoPorId() {
        Curso curso = new Curso();
        when(cursoService.obtenerCursoPorId(anyLong())).thenReturn(Optional.of(curso));

        ResponseEntity<?> response = cursoController.obtenerCursoPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    public void testObtenerCursoPorId_NotFound() {
        when(cursoService.obtenerCursoPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = cursoController.obtenerCursoPorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró el curso", response.getBody());
    }

    @Test
    public void testCrearCurso() {
        Curso curso = new Curso();
        when(cursoService.guardarCurso(any(Curso.class))).thenReturn(curso);

        ResponseEntity<Curso> response = cursoController.crearCurso(curso);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    public void testActualizarCurso() {
        Curso curso = new Curso();
        when(cursoService.actualizarCurso(anyLong(), any(Curso.class))).thenReturn(curso);

        ResponseEntity<Curso> response = cursoController.actualizarCurso(1L, curso);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    @Test
    public void testActualizarCurso_NotFound() {
        when(cursoService.actualizarCurso(anyLong(), any(Curso.class)))
                .thenThrow(new RuntimeException("Curso no encontrado con ID: 1"));

        ResponseEntity<Curso> response = cursoController.actualizarCurso(1L, new Curso());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testEliminarCurso() {
        doNothing().when(cursoService).eliminarCurso(anyLong());

        ResponseEntity<Void> response = cursoController.eliminarCurso(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testEliminarCurso_NotFound() {
        doThrow(new RuntimeException("Curso no encontrado con ID: 1")).when(cursoService).eliminarCurso(anyLong());

        ResponseEntity<Void> response = cursoController.eliminarCurso(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}