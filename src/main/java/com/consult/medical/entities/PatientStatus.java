package com.consult.medical.entities;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ESTADOS_SALUD")
public class PatientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer code;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "numero_identificacion")
    private Integer numberId;

    @Column(name = "nombres")
    private String names;

    @Column(name = "apellidos")
    private String surnames;

    @Column(name = "azucar")
    private Integer sugar;

    @Column(name = "grasa")
    private Integer fat;

    @Column(name = "oxigeno")
    private Integer oxygen;

    @Column(name = "estado")
    private String status;

    @Column(name = "activo")
    private Boolean active;

}
