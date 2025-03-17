package com.proof.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administrativo")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Administrativo extends Persona {

    @NotBlank(message = "El cargo no puede estar vacío")
    private String cargo;

    @NotBlank(message = "El departamento no puede estar vacío")
    private String departamento;
}
