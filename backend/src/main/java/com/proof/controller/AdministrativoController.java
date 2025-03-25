package com.proof.controller;

import com.proof.model.Administrativo;
import com.proof.service.AdministrativoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Clase controlador de Administrativo AdministrativoController
 * Clase que se encarga de manejar las peticiones HTTP relacionadas con los
 * administrativos
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Administrativos", description = "API para la gestión de administrativos")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/administrativos")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    /**
     * Método que se encarga de listar todos los administrativos
     * 
     * @return ResponseEntity con la lista de administrativos
     */
    @Operation(summary = "Listar todos los administrativos", description = "Obtiene una lista de todos los administrativos registrados")
    @GetMapping
    public ResponseEntity<?> listarAdministrativos() {
        List<Administrativo> administrativos = administrativoService.listarAdministrativos();
        if (administrativos.isEmpty()) {
            return new ResponseEntity<>("No se encontraron administrativos", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(administrativos, HttpStatus.OK);
        }
    }

    /**
     * Método que se encarga de obtener un administrativo por ID
     * 
     * @param id ID del administrativo a obtener
     * @return ResponseEntity con el administrativo obtenido
     */
    @Operation(summary = "Obtener un administrativo por ID", description = "Obtiene un administrativo específico mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAdministrativoPorId(@PathVariable("id") Long id) {
        Optional<Administrativo> administrativo = administrativoService.obtenerAdministrativoPorId(id);
        if (administrativo.isPresent()) {
            return new ResponseEntity<>(administrativo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró el administrativo", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Método que se encarga de crear un nuevo administrativo
     * 
     * @param administrativo administrativo a guardar
     * @return ResponseEntity con el administrativo guardado
     */
    @Operation(summary = "Crear un nuevo administrativo", description = "Crea un nuevo administrativo con los datos proporcionados")
    @PostMapping
    public ResponseEntity<Administrativo> crearAdministrativo(@RequestBody Administrativo administrativo) {
        Administrativo nuevoAdministrativo = administrativoService.guardarAdministrativo(administrativo);
        return ResponseEntity.ok(nuevoAdministrativo);
    }

    /**
     * Método que se encarga de actualizar un administrativo existente
     * 
     * @param id             ID del administrativo a actualizar
     * @param administrativo administrativo con los datos actualizados
     * @return ResponseEntity con el administrativo actualizado
     */
    @Operation(summary = "Actualizar un administrativo", description = "Actualiza los datos de un administrativo existente mediante su ID")
    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> actualizarAdministrativo(@PathVariable("id") Long id,
            @RequestBody Administrativo administrativo) {
        try {
            Administrativo administrativoActualizado = administrativoService.actualizarAdministrativo(id,
                    administrativo);
            return ResponseEntity.ok(administrativoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método que se encarga de eliminar un administrativo
     * 
     * @param id ID del administrativo a eliminar
     * @return ResponseEntity con el administrativo eliminado
     */
    @Operation(summary = "Eliminar un administrativo", description = "Elimina un administrativo existente mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrativo(@PathVariable("id") Long id) {
        try {
            administrativoService.eliminarAdministrativo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
