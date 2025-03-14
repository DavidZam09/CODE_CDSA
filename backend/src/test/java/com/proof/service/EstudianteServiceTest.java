package com.proof.service;

import com.proof.model.Estudiante;
import com.proof.repository.EstudianteRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante estudiante1;
    private Estudiante estudiante2;

    @BeforeEach
    void setUp() {
        estudiante1 = new Estudiante();
        estudiante1.setId_Persona(1L);
        estudiante1.setNumeroMatricula("12345");
        estudiante1.setGrado("10");

        estudiante2 = new Estudiante();
        estudiante2.setId_Persona(2L);
        estudiante2.setNumeroMatricula("67890");
        estudiante2.setGrado("11");
    }

    @Test
    void listarEstudiantes_DeberiaRetornarListaDeEstudiantes() {
        List<Estudiante> listaEstudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteRepository.findAll()).thenReturn(listaEstudiantes);

        List<Estudiante> resultado = estudianteService.listarEstudiantes();

        assertEquals(2, resultado.size());
        verify(estudianteRepository, times(1)).findAll();
    }

    @Test
    void listarEstudiantes_SinRegistros_DeberiaLanzarExcepcion() {
        when(estudianteRepository.findAll()).thenReturn(List.of());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            estudianteService.listarEstudiantes();
        });

        assertEquals("No hay estudiantes registrados.", exception.getMessage());
    }

    @Test
    void guardarEstudiante_DeberiaGuardarYRetornarEstudiante() {
        when(estudianteRepository.save(estudiante1)).thenReturn(estudiante1);

        Estudiante resultado = estudianteService.guardarEstudiante(estudiante1);

        assertNotNull(resultado);
        assertEquals("12345", resultado.getNumeroMatricula());
        verify(estudianteRepository, times(1)).save(estudiante1);
    }

    @Test
    void actualizarEstudiante_DeberiaActualizarYRetornarEstudiante() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante1));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante1);

        Estudiante estudianteActualizado = new Estudiante();
        estudianteActualizado.setNumeroMatricula("99999");
        estudianteActualizado.setGrado("12");

        Estudiante resultado = estudianteService.actualizarEstudiante(1L, estudianteActualizado);

        assertNotNull(resultado);
        assertEquals("99999", resultado.getNumeroMatricula());
        assertEquals("12", resultado.getGrado());
        verify(estudianteRepository, times(1)).findById(1L);
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @Test
    void actualizarEstudiante_EstudianteNoEncontrado_DeberiaLanzarExcepcion() {
        when(estudianteRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            estudianteService.actualizarEstudiante(1L, estudiante1);
        });

        assertEquals("Estudiante no encontrado con ID: 1", exception.getMessage());
    }

    @Test
    void eliminarEstudiante_DeberiaEliminarEstudiante() {
        doNothing().when(estudianteRepository).deleteById(1L);

        estudianteService.eliminarEstudiante(1L);

        verify(estudianteRepository, times(1)).deleteById(1L);
    }
}
