package com.proof.repository;

import com.proof.model.Curso;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Curso
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
@Tag(name = "Curso Repository", description = "Operaciones relacionadas con la entidad Curso")
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
