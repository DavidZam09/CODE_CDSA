package com.proof.repository;

import com.proof.model.Persona;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Persona
 * 
 * @autor David Orlando Velez Zamora
 */
@Tag(name = "Persona Repository", description = "Operaciones relacionadas con la entidad Persona")
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
