package com.proof.model;

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
public class Estudiante extends Persona {
    @NotBlank(message = "El número de matrícula no puede estar vacío")
    private String numeroMatricula;

    @NotBlank(message = "El grado no puede estar vacío")
    private String grado;
}
