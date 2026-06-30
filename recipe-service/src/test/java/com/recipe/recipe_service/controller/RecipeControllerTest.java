package com.recipe.recipe_service.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.recipe.recipe_service.model.Recipe;
import com.recipe.recipe_service.service.RecipeService;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Test
    void listarRecetas() throws Exception {
        List<Recipe> recipes = List.of(
            new Recipe(
                1,
                1,
                "Infección",
                "Amoxicilina",
                "500mg",
                7,
                "Reposo",
                LocalDate.parse("2026-06-02")
            )
        );
        when(recipeService.obtenerRecetas()).thenReturn(recipes);
        mockMvc.perform(get("/recetas"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarReceta() throws Exception {
        String recipeJson = """
                {
                    "mascotaId": 1,
                    "diagnostico": "Infección",
                    "medicacion": "Amoxicilina",
                    "dosis": "500mg",
                    "duracion": 7,
                    "recomendaciones": "Reposo",
                    "fecha": "2026-06-02"
                }
                """;
        Recipe recipeGuardada = new Recipe(
            1,
            1,
            "Infección",
            "Amoxicilina",
            "500mg",
            7,
            "Reposo",
            LocalDate.parse("2026-06-02")
        );
        when(recipeService.guardarReceta(any(Recipe.class))).thenReturn(recipeGuardada);
        mockMvc.perform(post("/recetas/agregar")
                .contentType("application/json")
                .content(recipeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnostico").value("Infección"));
    }

    @Test
    void obtenerPorId() throws Exception {
        Recipe recipe = new Recipe(
            1,
            1,
            "Infección",
            "Amoxicilina",
            "500mg",
            7,
            "Reposo",
            LocalDate.parse("2026-06-02")
        );
        when(recipeService.obtenerPorId(1)).thenReturn(recipe);
        mockMvc.perform(get("/recetas/obtener/1"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarReceta() throws Exception {
        String recipeJson = """
                {
                    "mascotaId": 1,
                    "diagnostico": "Infección leve",
                    "medicacion": "Amoxicilina",
                    "dosis": "250mg",
                    "duracion": 5,
                    "recomendaciones": "Reposo y agua",
                    "fecha": "2026-06-02"
                }
                """;
        when(recipeService.actualizarReceta(eq(1), any(Recipe.class)))
                .thenReturn(new Recipe(
                    1,
                    1,
                    "Infección leve",
                    "Amoxicilina",
                    "250mg",
                    5,
                    "Reposo y agua",
                    LocalDate.parse("2026-06-02")
                ));
        mockMvc.perform(put("/recetas/actualizar/1")
                .contentType("application/json")
                .content(recipeJson))
                .andExpect(status().isOk())
                .andExpect(content().string("La receta ha sido actualizada con éxito"));
    }

    @Test
    void eliminarReceta() throws Exception {
        doNothing().when(recipeService).eliminarReceta(1);
        mockMvc.perform(delete("/recetas/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("La receta ha sido eliminada correctamente"));
    }
}