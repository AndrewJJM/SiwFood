package it.uniroma3.siw.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.RecipeValidator;
import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.service.RecipeService;
import jakarta.validation.Valid;

@Controller
public class RecipeController {
	  @Autowired RecipeService recipeService;
	  @Autowired RecipeValidator recipeValidator;
	  
	  @GetMapping("/admin/newRecipe")
	  public String formNewRecipe(Model model) {
		  Recipe recipe = new Recipe();
		  recipe.setIngredients(new HashSet<Ingredient>());
		  model.addAttribute("recipe", recipe);
		  return "formNewRecipe.html";
	  }
	  
	  @PostMapping("/admin/recipes")
	  public String newRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult, Model model) {
		  this.recipeValidator.validate(recipe, bindingResult);
		  if (bindingResult.hasErrors()) { 			//il binding controlla i vincoli espliciti semplici
			  return "formNewRecipe.html";
		  } else {
			  this.recipeService.save(recipe); 
			  model.addAttribute("recipe", recipe); //per il redirect
			  return "redirect:/recipes/" + recipe.getId(); //per evitare la doppia richiesta in caso di refresh della pagina
		  }
	  }
	  
	  @GetMapping("/recipes/{id}")
	  public String getRecipe(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("recipe", this.recipeService.findById(id));
		  return "recipe.html";
	  }
	  
	  @GetMapping("/recipes")
	  public String listRecipes(Model model){
		  model.addAttribute("recipes", this.recipeService.findAll());
		  return "recipes.html";
	  }

}
