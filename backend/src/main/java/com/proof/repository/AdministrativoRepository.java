package com.proof.repository;

import com.proof.model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de la entidad Administrativo
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
}
