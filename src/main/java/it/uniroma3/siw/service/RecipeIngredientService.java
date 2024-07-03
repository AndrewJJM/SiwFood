package it.uniroma3.siw.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.model.RecipeIngredient;
import it.uniroma3.siw.repository.RecipeIngredientRepository;

@Service
public class RecipeIngredientService {
	
	@Autowired
	private RecipeIngredientRepository recipeIngredientRepository;
	
	/* rimuove ingrediente dalla ricetta */ 
	public void removeIngredient(Set<RecipeIngredient> recipeIngredients, Ingredient ingredientToRemove) {
	    Iterator<RecipeIngredient> iterator = recipeIngredients.iterator();
	    while (iterator.hasNext()) {
	        RecipeIngredient recipeIngredient = iterator.next();
	        if (recipeIngredient.getIngredient().equals(ingredientToRemove)) {
	            iterator.remove();  // Safe removal
	            this.recipeIngredientRepository.delete(recipeIngredient);  // Remove from database
	        }
	    }
	}

	/* aggiunge ingrediente alla ricetta */ 
	public void addIngredient(Recipe recipe, Ingredient ingredient) {
	    RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredient);
	    if (!recipe.getRecipeIngredients().contains(recipeIngredient)) {
	        recipe.getRecipeIngredients().add(recipeIngredient);
	        this.recipeIngredientRepository.save(recipeIngredient);  // Save to database
	    }
	}
	
}
