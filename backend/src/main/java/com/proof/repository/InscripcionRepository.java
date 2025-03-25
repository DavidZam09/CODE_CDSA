package com.proof.repository;

import com.proof.model.Inscripcion;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Inscripcion
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
@Tag(name = "Inscripcion Repository", description = "Operaciones relacionadas con la entidad Inscripcion")
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
