package com.recipe.recipe_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecipeDetalleDTO {

    private String nombrePaciente;
    private String edad;
    private String raza;
    private String diagnostico;
    private String medicacion;
    private String dosis;
    private Integer duracion;
    private String recomendaciones;
    private LocalDate fecha;
}
