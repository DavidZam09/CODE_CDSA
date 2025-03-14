package com.proof.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Curso; // ✅ Se eliminó el guion bajo para seguir la convención de Java

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int creditos; // ✅ Los créditos de un curso deben ser numéricos, no una fecha

    @Column(nullable = false, unique = true)
    private Long idProfesor; // ✅ Se cambió a Long para representar una relación con el profesor
}
