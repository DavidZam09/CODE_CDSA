package com.proof.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Clase que representa a un estudiante de la institución educativa
 * 
 * @autor David Orlando Velez Zamora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id_persona")
@Schema(description = "Clase que representa a un estudiante de la institución educativa")
public class Estudiante extends Persona {

    @Schema(description = "Número de matrícula del estudiante", example = "2021100001")
    @NotBlank(message = "El número de matrícula no puede estar vacío")
    private String numeroMatricula;

    @Schema(description = "Grado al que pertenece el estudiante", example = "11")
    @NotBlank(message = "El grado no puede estar vacío")
    private String grado;
}
