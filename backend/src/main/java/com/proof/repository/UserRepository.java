package com.proof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proof.model.User;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Repositorio de la entidad User
 * 
 * @autor David Orlando Velez Zamora
 */

@Tag(name = "User Repository", description = "Operaciones relacionadas con la entidad User")
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
