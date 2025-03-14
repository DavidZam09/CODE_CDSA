package com.proof.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administrativo")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Administrativo extends Persona {

    private String cargo;

    private LocalDate departamento;
}
