package com.proof.repository;

import com.proof.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Inscripcion
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
