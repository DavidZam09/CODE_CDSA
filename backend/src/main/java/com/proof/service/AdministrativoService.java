package com.proof.service;

import com.proof.model.Administrativo;
import com.proof.repository.AdministrativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    // Listar todos los administrativos
    public List<Administrativo> listarAdministrativos() {
        return administrativoRepository.findAll();
    }

    // Obtener un administrativo por ID
    public Optional<Administrativo> obtenerAdministrativoPorId(Long id) {
        return administrativoRepository.findById(id);
    }

    // Guardar un administrativo
    public Administrativo guardarAdministrativo(Administrativo administrativo) {
        return administrativoRepository.save(administrativo);
    }

    // Actualizar un administrativo
    public Administrativo actualizarAdministrativo(Long id, Administrativo administrativoActualizado) {
        return administrativoRepository.findById(id)
            .map(administrativo -> {
                administrativo.setCargo(administrativoActualizado.getCargo());
                administrativo.setDepartamento(administrativoActualizado.getDepartamento());
                return administrativoRepository.save(administrativo);
            }).orElseThrow(() -> new RuntimeException("Administrativo no encontrado con ID: " + id));
    }

    // Eliminar un administrativo
    public void eliminarAdministrativo(Long id) {
        if (!administrativoRepository.existsById(id)) {
            throw new RuntimeException("Administrativo no encontrado con ID: " + id);
        }
        administrativoRepository.deleteById(id);
    }
}
