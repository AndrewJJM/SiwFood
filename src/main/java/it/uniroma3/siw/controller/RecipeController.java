package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.service.RecipeService;

@Controller
public class RecipeController {
	  @Autowired RecipeService recipeService;
	  
	  @GetMapping("/newRecipe")
	  public String formNewRecipe(Model model) {
		  model.addAttribute("recipe", new Recipe());
		  return "formNewRecipe.html";
	  }
	  
	  @PostMapping("/recipes")
	  public String newRecipe(@ModelAttribute("recipe") Recipe recipe, Model model) {
		  this.recipeService.save(recipe); 
		  model.addAttribute("recipe", recipe); //per il redirect
		  return "redirect:recipes/" + recipe.getId(); //per evitare la doppia richiesta in caso di refresh della pagina
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
