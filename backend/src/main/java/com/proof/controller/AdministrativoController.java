package com.proof.controller;

import com.proof.model.Administrativo;
import com.proof.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrativos")
public class AdministrativoController {

    @Autowired
    private AdministrativoService administrativoService;

    // Obtener todos los administrativos
    @GetMapping
    public List<Administrativo> listarAdministrativos() {
        return administrativoService.listarAdministrativos();
    }

    // Obtener un administrativo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Administrativo> obtenerAdministrativoPorId(@PathVariable Long id) {
        Optional<Administrativo> administrativo = administrativoService.obtenerAdministrativoPorId(id);
        return administrativo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo administrativo
    @PostMapping
    public ResponseEntity<Administrativo> crearAdministrativo(@RequestBody Administrativo administrativo) {
        Administrativo nuevoAdministrativo = administrativoService.guardarAdministrativo(administrativo);
        return ResponseEntity.ok(nuevoAdministrativo);
    }

    // Actualizar un administrativo existente
    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> actualizarAdministrativo(@PathVariable Long id,
            @RequestBody Administrativo administrativo) {
        try {
            Administrativo administrativoActualizado = administrativoService.actualizarAdministrativo(id,
                    administrativo);
            return ResponseEntity.ok(administrativoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un administrativo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrativo(@PathVariable Long id) {
        try {
            administrativoService.eliminarAdministrativo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
