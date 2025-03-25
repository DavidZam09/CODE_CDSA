package com.proof.repository;

import com.proof.model.Profesor;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Profesor
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Profesor Repository", description = "Operaciones relacionadas con la entidad Profesor")
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
