package com.proof.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profesor")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Profesor extends Persona {

    private String especialidad;

    private LocalDate fechaContratacion;
}
