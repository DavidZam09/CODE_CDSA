package com.proof.controller;

import com.proof.model.Administrativo;
import com.proof.service.AdministrativoService;
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
