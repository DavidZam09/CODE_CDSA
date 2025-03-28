package com.proof.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a un usuario del sistema
 * 
 * @autor David Orlando Velez Zamora
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
@Schema(description = "Clase que representa a un usuario del sistema")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Schema(description = "Identificador único del usuario", example = "1")
    Integer id;

    @Basic
    @Column(nullable = false)
    @Schema(description = "Nombre de usuario único", example = "johndoe")
    String username;

    @Column(nullable = false)
    @Schema(description = "Apellido del usuario", example = "Doe")
    String lastname;

    @Schema(description = "Nombre del usuario", example = "John")
    String firstname;

    @Schema(description = "País del usuario", example = "Colombia")
    String country;

    @Schema(description = "Contraseña del usuario", example = "password123")
    String password;
    
    @Enumerated(EnumType.STRING)
    @Schema(description = "Rol del usuario en el sistema", example = "ADMIN")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}