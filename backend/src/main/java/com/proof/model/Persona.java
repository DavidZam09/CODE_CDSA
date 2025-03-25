package com.proof.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Clase que representa a una persona de la institución educativa
 * 
 * @autor David Orlando Velez Zamora
 */
// Se usa si quieres herencia en la base de datos
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Se usa si quieres herencia en la base de datos
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Estudiante.class, name = "estudiante"),
        @JsonSubTypes.Type(value = Profesor.class, name = "profesor"),
        @JsonSubTypes.Type(value = Administrativo.class, name = "administrativo")
})
@Schema(description = "Clase que representa a una persona de la institución educativa")
// Se usa si quieres herencia en la base de datos
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador de la persona", example = "1")
    private Long id_Persona;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre de la persona", example = "David")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Schema(description = "Apellido de la persona", example = "Velez")
    private String apellido;

    @NotNull(message = "La fecha de nacimiento no puede estar vacío")
    @Schema(description = "Fecha de nacimiento de la persona", example = "2000-01-01")
    private LocalDate fechaNacimiento;

    @Email(message = "El email debe ser válido")
    @NotBlank(message = "El email no puede estar vacío")
    @Schema(description = "Correo electrónico de la persona", example = "email@email.com")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener exactamente 10 dígitos numéricos")
    @Schema(description = "Teléfono de la persona", example = "1234567890")
    private String telefono;
}
