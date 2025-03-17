package com.proof.model;

import java.time.LocalDate;

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
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_inscripcion;

    @Column(nullable = false)
    private Long id_estudiante;

    @Column(nullable = false)
    private Long id_curso;

    @Column(nullable = false)
    private LocalDate fecha_inscripcion;

}
