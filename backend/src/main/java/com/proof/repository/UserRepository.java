package com.proof.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proof.model.User;

/**
 * Repositorio de la entidad User
 * 
 * @autor David Orlando Velez Zamora
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
