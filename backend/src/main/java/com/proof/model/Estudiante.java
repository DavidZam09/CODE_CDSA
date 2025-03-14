package com.proof.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Estudiante extends Persona {
    private String numeroMatricula;
    private String grado;
}
