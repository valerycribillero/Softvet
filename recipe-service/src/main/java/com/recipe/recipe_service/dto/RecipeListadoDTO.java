package com.recipe.recipe_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RecipeListadoDTO {

    private Integer MascotaId;
    private String diagnostico;
    private String medicacion;
    private LocalDate fecha;
}
