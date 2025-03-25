package com.proof.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Clase que representa a un profesor de la institución educativa")
public class Profesor extends Persona {

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Schema(description = "Especialidad del profesor", example = "Matemáticas")
    private String especialidad;

    @NotNull(message = "La fecha de contratación no puede ser nula")
    @PastOrPresent(message = "La fecha de contratación no puede ser una fecha futura")
    @Schema(description = "Fecha de contratación del profesor", example = "2021-10-01")
    private LocalDate fechaContratacion;
}
