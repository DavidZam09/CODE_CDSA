package com.proof.repository;

import com.proof.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Curso
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
