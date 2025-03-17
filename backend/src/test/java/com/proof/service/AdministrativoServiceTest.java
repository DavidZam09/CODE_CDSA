package com.proof.service;

import com.proof.model.Administrativo;
import com.proof.repository.AdministrativoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AdministrativoServiceTest {

    @Mock
    private AdministrativoRepository administrativoRepository;

    @InjectMocks
    private AdministrativoService administrativoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarAdministrativos() {
        Administrativo admin1 = new Administrativo();
        Administrativo admin2 = new Administrativo();
        List<Administrativo> expectedList = Arrays.asList(admin1, admin2);

        when(administrativoRepository.findAll()).thenReturn(expectedList);

        List<Administrativo> actualList = administrativoService.listarAdministrativos();

        assertEquals(expectedList, actualList);
        verify(administrativoRepository, times(1)).findAll();
    }

    @Test
    void obtenerAdministrativoPorId() {
        Administrativo admin = new Administrativo();
        Long id = 1L;

        when(administrativoRepository.findById(id)).thenReturn(Optional.of(admin));

        Optional<Administrativo> actualAdmin = administrativoService.obtenerAdministrativoPorId(id);

        assertTrue(actualAdmin.isPresent());
        assertEquals(admin, actualAdmin.get());
        verify(administrativoRepository, times(1)).findById(id);
    }

    @Test
    void guardarAdministrativo() {
        Administrativo admin = new Administrativo();

        when(administrativoRepository.save(admin)).thenReturn(admin);

        Administrativo savedAdmin = administrativoService.guardarAdministrativo(admin);

        assertEquals(admin, savedAdmin);
        verify(administrativoRepository, times(1)).save(admin);
    }

    @Test
    public void testActualizarAdministrativo_Success() {
        Administrativo administrativo = new Administrativo();
        // administrativo.setId(1L);
        administrativo.setCargo("Old Cargo");
        administrativo.setDepartamento("Old Department");

        Administrativo administrativoActualizado = new Administrativo();
        administrativoActualizado.setCargo("New Cargo");
        administrativoActualizado.setDepartamento("New Department");

        when(administrativoRepository.findById(anyLong())).thenReturn(Optional.of(administrativo));
        when(administrativoRepository.save(any(Administrativo.class))).thenReturn(administrativo);

        Administrativo result = administrativoService.actualizarAdministrativo(1L, administrativoActualizado);

        assertEquals("New Cargo", result.getCargo());
        assertEquals("New Department", result.getDepartamento());
    }

    @Test
    public void testActualizarAdministrativo_NotFound() {
        Administrativo administrativoActualizado = new Administrativo();
        administrativoActualizado.setCargo("New Cargo");
        administrativoActualizado.setDepartamento("New Department");

        when(administrativoRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            administrativoService.actualizarAdministrativo(1L, administrativoActualizado);
        });

        assertEquals("Administrativo no encontrado con ID: 1", exception.getMessage());
    }

    @Test
    void eliminarAdministrativo() {
        Long id = 1L;

        when(administrativoRepository.existsById(id)).thenReturn(true);

        administrativoService.eliminarAdministrativo(id);

        verify(administrativoRepository, times(1)).existsById(id);
        verify(administrativoRepository, times(1)).deleteById(id);
    }

    @Test
    void eliminarAdministrativo_NotFound() {
        Long id = 1L;

        when(administrativoRepository.existsById(id)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            administrativoService.eliminarAdministrativo(id);
        });

        assertEquals("Administrativo no encontrado con ID: " + id, exception.getMessage());
        verify(administrativoRepository, times(1)).existsById(id);
        verify(administrativoRepository, times(0)).deleteById(id);
    }
}
