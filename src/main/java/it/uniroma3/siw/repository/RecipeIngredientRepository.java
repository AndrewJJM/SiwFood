package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.model.RecipeIngredient;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {

	public List<RecipeIngredient> findByRecipe(Recipe recipe);

}
