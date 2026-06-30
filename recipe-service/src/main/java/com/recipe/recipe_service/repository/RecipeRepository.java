package com.recipe.recipe_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.recipe_service.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
