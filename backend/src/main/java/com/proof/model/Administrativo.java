package com.proof.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase que representa a un administrativo de la institución educativa
 * 
 * @autor David Orlando Velez Zamora
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "administrativo")
@PrimaryKeyJoinColumn(name = "id_persona")
@Schema(description = "Clase que representa a un administrativo de la institución educativa")
public class Administrativo extends Persona {

    @Schema(description = "Cargo del administrativo", example = "Coordinador")
    @NotBlank(message = "El cargo no puede estar vacío")
    private String cargo;

    @Schema(description = "Departamento al que pertenece el administrativo", example = "Recursos Humanos")
    @NotBlank(message = "El departamento no puede estar vacío")
    private String departamento;
}
