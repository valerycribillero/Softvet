package com.recipe.recipe_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.recipe_service.dto.RecipeDetalleDTO;
import com.recipe.recipe_service.dto.RecipeListadoDTO;
import com.recipe.recipe_service.model.Recipe;
import com.recipe.recipe_service.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/recetas")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Operation(summary = "Listar recetas", description = "Obtiene una lista de todas las recetas registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<Recipe> listarRecetas() {
        return recipeService.obtenerRecetas();
    }

    @Operation(summary = "Agregar receta", description = "Registra una nueva receta en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta registrada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/agregar")
    public Recipe guardRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeService.guardarReceta(recipe);
    }

    @Operation(summary = "Obtener receta por ID", description = "Busca una receta por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta encontrada"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/obtener/{id}")
    public Recipe obtenerRecipe(@PathVariable Integer id) {
        return recipeService.obtenerPorId(id);
    }

    @Operation(summary = "Eliminar receta", description = "Elimina una receta por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        recipeService.eliminarReceta(id);
        return "La receta ha sido eliminada correctamente";
    }

    @Operation(summary = "Actualizar receta", description = "Actualiza los datos de una receta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receta actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        recipeService.actualizarReceta(id, recipe);
        return "La receta ha sido actualizada con éxito";
    }

    @Operation(summary = "Listar recetas DTO", description = "Obtiene una lista simplificada de recetas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/listar-dto")
    public List<RecipeListadoDTO> listarDTO() {
        return recipeService.listarDTO();
    }

    @Operation(summary = "Obtener detalle de receta", description = "Obtiene el detalle completo de una receta incluyendo datos de la mascota")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle obtenido correctamente"),
        @ApiResponse(responseCode = "404", description = "Receta no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}/detalle")
    public RecipeDetalleDTO obtenerDetalle(@PathVariable Integer id) {
        return recipeService.obtenerDetalle(id);
    }
}