package com.recipe.recipe_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.recipe.recipe_service.dto.RecipeDetalleDTO;
import com.recipe.recipe_service.dto.RecipeListadoDTO;
import com.recipe.recipe_service.model.Mascota;
import com.recipe.recipe_service.model.Recipe;
import com.recipe.recipe_service.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List <Recipe> obtenerRecetas() {
        return recipeRepository.findAll();
    }

    public Recipe guardarReceta(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe obtenerPorId(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public void eliminarReceta(Integer id) {
        recipeRepository.deleteById(id);
    }
    public Recipe actualizarReceta(Integer id, Recipe recipe) {
        recipe.setId(id);
        return recipeRepository.save(recipe);
    }
    public List<RecipeListadoDTO> listarDTO(){
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeListadoDTO> lista = new ArrayList<>();

        for(Recipe r: recipes) {
            RecipeListadoDTO dto= new RecipeListadoDTO();
            dto.setMascotaId(r.getMascotaId());
            dto.setDiagnostico(r.getDiagnostico());
            dto.setMedicacion(r.getMedicacion());
            dto.setFecha(r.getFecha());
            lista.add(dto);
        }
        return lista;
    }
    public RecipeDetalleDTO obtenerDetalle(Integer recipeId) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isEmpty()) return null;
        
        Recipe recipe = recipeOpt.get();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/mascota/" + recipe.getMascotaId();
        Mascota mascota = restTemplate.getForObject(url, Mascota.class);

        RecipeDetalleDTO dto = new RecipeDetalleDTO();

        dto.setNombrePaciente(mascota.getNombre());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());

        dto.setDiagnostico(recipe.getDiagnostico());
        dto.setMedicacion(recipe.getMedicacion());
        dto.setDosis(recipe.getDosis());
        dto.setDuracion(recipe.getDuracion());
        dto.setRecomendaciones(recipe.getRecomendaciones());
        dto.setFecha(recipe.getFecha());

        return dto;
    }


}
