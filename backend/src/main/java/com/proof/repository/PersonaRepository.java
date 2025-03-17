package com.proof.repository;

import com.proof.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Persona
 * 
 * @autor David Orlando Velez Zamora
 */
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
