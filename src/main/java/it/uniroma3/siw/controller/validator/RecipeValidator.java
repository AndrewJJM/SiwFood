package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.repository.RecipeRepository;

/************************************************** Questa classe implementa validazioni custom lato server********************************************/

@Component
public class RecipeValidator implements Validator {
	@Autowired
	private RecipeRepository recipeRepository;

	//Maybe add ingredients check?
	@Override
	public void validate(Object o, Errors errors) {
		Recipe recipe = (Recipe)o;
		if (recipe.getName()!=null && recipe.getDescription()!=null 
				&& recipeRepository.existsByNameAndDescription(recipe.getName(), recipe.getDescription())) {
			errors.reject("recipe.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Recipe.class.equals(aClass);
	}

}
