package com.proof.repository;

import com.proof.model.Estudiante;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Estudiante
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Estudiante Repository", description = "Operaciones relacionadas con la entidad Estudiante")
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
