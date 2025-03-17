package com.proof.controller;

import com.proof.model.Administrativo;
import com.proof.service.AdministrativoService;
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

public class AdministrativoControllerTest {

    @Mock
    private AdministrativoService administrativoService;

    @InjectMocks
    private AdministrativoController administrativoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarAdministrativos() {
        List<Administrativo> administrativos = Arrays.asList(new Administrativo(), new Administrativo());
        when(administrativoService.listarAdministrativos()).thenReturn(administrativos);

        ResponseEntity<?> response = administrativoController.listarAdministrativos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administrativos, response.getBody());
    }

    @Test
    public void testListarAdministrativos_NotFound() {
        when(administrativoService.listarAdministrativos()).thenReturn(Arrays.asList());

        ResponseEntity<?> response = administrativoController.listarAdministrativos();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontraron administrativos", response.getBody());
    }

    @Test
    public void testObtenerAdministrativoPorId() {
        Administrativo administrativo = new Administrativo();
        when(administrativoService.obtenerAdministrativoPorId(anyLong())).thenReturn(Optional.of(administrativo));

        ResponseEntity<?> response = administrativoController.obtenerAdministrativoPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administrativo, response.getBody());
    }

    @Test
    public void testObtenerAdministrativoPorId_NotFound() {
        when(administrativoService.obtenerAdministrativoPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<?> response = administrativoController.obtenerAdministrativoPorId(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se encontró el administrativo", response.getBody());
    }

    @Test
    public void testCrearAdministrativo() {
        Administrativo administrativo = new Administrativo();
        when(administrativoService.guardarAdministrativo(any(Administrativo.class))).thenReturn(administrativo);

        ResponseEntity<Administrativo> response = administrativoController.crearAdministrativo(administrativo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administrativo, response.getBody());
    }

    @Test
    public void testActualizarAdministrativo() {
        Administrativo administrativo = new Administrativo();
        when(administrativoService.actualizarAdministrativo(anyLong(), any(Administrativo.class)))
                .thenReturn(administrativo);

        ResponseEntity<Administrativo> response = administrativoController.actualizarAdministrativo(1L, administrativo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(administrativo, response.getBody());
    }

    @Test
    public void testActualizarAdministrativo_NotFound() {
        when(administrativoService.actualizarAdministrativo(anyLong(), any(Administrativo.class)))
                .thenThrow(new RuntimeException("Administrativo no encontrado con ID: 1"));

        ResponseEntity<Administrativo> response = administrativoController.actualizarAdministrativo(1L,
                new Administrativo());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testEliminarAdministrativo() {
        doNothing().when(administrativoService).eliminarAdministrativo(anyLong());

        ResponseEntity<Void> response = administrativoController.eliminarAdministrativo(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testEliminarAdministrativo_NotFound() {
        doThrow(new RuntimeException("Administrativo no encontrado con ID: 1")).when(administrativoService)
                .eliminarAdministrativo(anyLong());

        ResponseEntity<Void> response = administrativoController.eliminarAdministrativo(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}