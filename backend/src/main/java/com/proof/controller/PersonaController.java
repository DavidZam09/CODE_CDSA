package com.proof.controller;

import com.proof.model.Persona;
import com.proof.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * PersonaController Class controlador de Persona
 * PersonaController es la clase que se encarga de manejar las peticiones HTTP
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Personas", description = "API para la gestión de personas")
@RestController
@RequestMapping("/api/v1/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    /**
     * Método que se encarga de listar todas las personas
     * 
     * @return ResponseEntity con la lista de personas
     */
    @Operation(summary = "Listar todas las personas", description = "Obtiene una lista de todas las personas registradas")
    @GetMapping
    public List<Persona> listarPersonas() {
        return personaService.listarPersonas();
    }

    /**
     * Método que se encarga de obtener una persona por ID
     * 
     * @param id ID de la persona a obtener
     * @return ResponseEntity con la persona obtenida
     */
    @Operation(summary = "Obtener persona por ID", description = "Obtiene una persona específica mediante su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable("id") Long id) {
        Optional<Persona> persona = personaService.obtenerPersonaPorId(id);
        return persona.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Método que se encarga de guardar una nueva persona
     * 
     * @param persona persona a guardar
     * @return ResponseEntity con la persona guardada
     */
    @Operation(summary = "Crear nueva persona", description = "Guarda una nueva persona en el sistema")
    @PostMapping
    public ResponseEntity<Persona> crearPersona(@Valid @RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.guardarPersona(persona));
    }

    /**
     * Método que se encarga de actualizar una persona existente
     * 
     * @param id
     * @param personaActualizada
     * @return ResponseEntity con la persona actualizada
     */
    @Operation(summary = "Actualizar persona", description = "Actualiza los datos de una persona existente")
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable("id") Long id,
            @Valid @RequestBody Persona personaActualizada) {
        try {
            Persona persona = personaService.actualizarPersona(id, personaActualizada);
            return ResponseEntity.ok(persona);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método que se encarga de eliminar una persona
     * 
     * @param id ID de la persona a eliminar
     * @return ResponseEntity con la persona eliminada
     */
    @Operation(summary = "Eliminar persona", description = "Elimina una persona del sistema mediante su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable("id") Long id) {
        personaService.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
