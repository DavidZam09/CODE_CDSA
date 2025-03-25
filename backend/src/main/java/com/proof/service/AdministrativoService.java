package com.proof.service;

import com.proof.model.Administrativo;
import com.proof.repository.AdministrativoRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Administrativo
 * 
 * @autor David Orlando Velez Zamora
 */
@Service
@Tag(name = "Administrativo Service", description = "Operaciones relacionadas con la entidad Administrativo")
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    /**
     * Listar todos los administrativos
     * 
     * @return Lista de administrativos
     */
    @Operation(summary = "Listar todos los administrativos", description = "Obtiene una lista de todos los administrativos")
    public List<Administrativo> listarAdministrativos() {
        return administrativoRepository.findAll();
    }

    /**
     * Obtener un administrativo por ID
     * 
     * @param id ID del administrativo a obtener
     * @return Administrativo
     */
    @Operation(summary = "Obtener un administrativo por ID", description = "Obtiene un administrativo específico por su ID")
    public Optional<Administrativo> obtenerAdministrativoPorId(Long id) {
        return administrativoRepository.findById(id);
    }

    /**
     * Guardar un administrativo
     * 
     * @param administrativo Administrativo a guardar
     * @return Administrativo
     */
    @Operation(summary = "Guardar un administrativo", description = "Guarda un nuevo administrativo en la base de datos")
    public Administrativo guardarAdministrativo(@RequestBody(description = "Datos del administrativo a guardar") Administrativo administrativo) {
        return administrativoRepository.save(administrativo);
    }

    /**
     * Actualizar un administrativo
     * 
     * @param id                        ID del administrativo a actualizar
     * @param administrativoActualizado Administrativo actualizado
     * @return Administrativo
     */
    @Operation(summary = "Actualizar un administrativo", description = "Actualiza los datos de un administrativo existente")
    public Administrativo actualizarAdministrativo(Long id, @RequestBody(description = "Datos actualizados del administrativo") Administrativo administrativoActualizado) {
        return administrativoRepository.findById(id)
                .map(administrativo -> {
                    administrativo.setCargo(administrativoActualizado.getCargo());
                    administrativo.setDepartamento(administrativoActualizado.getDepartamento());
                    return administrativoRepository.save(administrativo);
                }).orElseThrow(() -> new RuntimeException("Administrativo no encontrado con ID: " + id));
    }

    /**
     * Eliminar un administrativo
     * 
     * @param id ID del administrativo a eliminar
     * @throws RuntimeException
     */
    @Operation(summary = "Eliminar un administrativo", description = "Elimina un administrativo existente por su ID")
    public void eliminarAdministrativo(Long id) {
        if (!administrativoRepository.existsById(id)) {
            throw new RuntimeException("Administrativo no encontrado con ID: " + id);
        }
        administrativoRepository.deleteById(id);
    }
}
