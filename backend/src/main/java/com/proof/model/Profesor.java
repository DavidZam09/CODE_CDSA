package com.proof.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

/**
 * Clase que representa a un profesor de la institución educativa
 * 
 * @autor David Orlando Velez Zamora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profesor")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Profesor extends Persona {

    @NotBlank(message = "La especialidad no puede estar vacía")
    private String especialidad;

    @NotNull(message = "La fecha de contratación no puede ser nula")
    private LocalDate fechaContratacion;
}
