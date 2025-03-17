package com.proof.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiante")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Estudiante extends Persona {
    @NotBlank(message = "El número de matrícula no puede estar vacío")
    private String numeroMatricula;

    @NotBlank(message = "El grado no puede estar vacío")
    private String grado;
}
