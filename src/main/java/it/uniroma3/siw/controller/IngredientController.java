package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.service.IngredientService;
import it.uniroma3.siw.service.RecipeService;


@Controller
public class IngredientController {
	@Autowired IngredientService ingredientservice;
	@Autowired RecipeService recipeservice;
	
	@GetMapping("/admin/updateIngredients/{id}")
	public String ingredientsForm(@PathVariable("id") Long id, Model model) {
			  model.addAttribute("recipe", this.recipeservice.findById(id)); //aggiungo la recipe al Modello partendo dall'id del percorso
			  model.addAttribute("Ingredient", new Ingredient());
			  return "manageIngredients.html";
	}
	@PostMapping("/admin/updateIngredients/{id}")
	public String newIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
	  //TODO this.recipeValidator.validate(recipe, bindingResult);
	  if (!bindingResult.hasErrors()) { 
		  this.ingredientservice.save(ingredient);
		  return "redirect:updateIngredients/" + id; //forzo il refresh della pagina
		  } else {
		  return "manageIngredients.html";
	  }
	}

}
