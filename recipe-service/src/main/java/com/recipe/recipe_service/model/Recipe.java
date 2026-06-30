package com.recipe.recipe_service.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La mascota es obligatoria")
    private Integer mascotaId;

    @NotBlank(message = "El diagnostico no puede estar vacío")
    private String diagnostico;

    @NotBlank(message = "El medicamento no puede estar vacío")
    private String medicacion;

    @NotBlank(message = "La dosis no puede estar vacía")
    private String dosis;

    @NotNull(message = "La duración es obligatoria")
    private Integer duracion;

    @NotBlank(message = "Las recomendaciones no pueden estar vacías")
    private String recomendaciones;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    
}
