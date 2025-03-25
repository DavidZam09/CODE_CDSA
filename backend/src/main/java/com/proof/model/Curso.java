package com.proof.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

/**
 * Clase que representa a un curso de la institución educativa
 * 
 * @autor David Orlando Velez Zamora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Clase que representa a un curso de la institución educativa")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador del curso", example = "1")
    private Long id_Curso;

    @Schema(description = "Nombre del curso", example = "Matemáticas")
    @Column(nullable = false)
    private String nombre;

    @Schema(description = "Descripción del curso", example = "Curso de matemáticas básicas")
    @Column(nullable = false)
    private String descripcion;

    @Schema(description = "Cantidad de créditos del curso", example = "4")
    @Column(nullable = false)
    private int creditos;

    @Schema(description = "Identificador del profesor que imparte el curso", example = "1")
    @Column(nullable = false, unique = true)
    private Long idProfesor;
}
