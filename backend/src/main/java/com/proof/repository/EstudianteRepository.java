package com.proof.repository;

import com.proof.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Estudiante
 * 
 * @autor David Orlando Velez Zamora
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
