package com.proof.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa a una inscripción de un estudiante a un curso
 * 
 * @autor David Orlando Velez Zamora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Clase que representa a una inscripción de un estudiante a un curso")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador de la inscripción", example = "1")
    private Long id_inscripcion;

    @Column(nullable = false)
    @Schema(description = "Identificador del estudiante", example = "1")
    private Long id_estudiante;

    @Column(nullable = false)
    @Schema(description = "Identificador del curso", example = "1")
    private Long id_curso;

    @Column(nullable = false)
    @Schema(description = "Fecha de inscripción", example = "2021-10-01")
    private LocalDate fecha_inscripcion;

}
