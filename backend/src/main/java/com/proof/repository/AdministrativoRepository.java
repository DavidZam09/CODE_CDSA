package com.proof.repository;

import com.proof.model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Repositorio de la entidad Administrativo
 * 
 * @autor David Orlando Velez Zamora
 */
@Repository
@Tag(name = "Administrativo Repository", description = "Operaciones relacionadas con la entidad Administrativo")
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {
}
