package com.proof.repository;

import com.proof.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Profesor
 * 
 * @autor David Orlando Velez Zamora
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}
