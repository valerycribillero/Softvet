package com.veterinaria.mascota.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La especie no puede estar vacía")
    private String especie;

    @NotBlank(message = "El sexo no puede estar vacía")
    private String sexo;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;
}