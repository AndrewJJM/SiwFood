package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.IngredientValidator;
import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.service.IngredientService;
import it.uniroma3.siw.service.RecipeService;


@Controller
public class RecipeIngredientController {
	@Autowired IngredientService ingredientService;
	@Autowired IngredientValidator ingredientValidator;
	@Autowired RecipeService recipeservice;
	
	@GetMapping("/ingredients")
	public String getIngredient(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());
		return "/user/manageIngredients.html";
	}

	@GetMapping("/user/updateIngredients/{id}")
	public String ingredientsForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recipe", this.recipeservice.findById(id)); //aggiungo la recipe al Modello partendo dall'id del percorso
		model.addAttribute("Ingredient", new Ingredient());
		return "manageIngredients.html";
	}
	@PostMapping("/user/updateIngredients/{id}")
	public String newUserIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
		//TODO this.recipeValidator.validate(recipe, bindingResult);
		if (!bindingResult.hasErrors()) { 
			this.ingredientService.save(ingredient);
			return "redirect:updateIngredients/" + id; //forzo il refresh della pagina
		} else {
			return "manageIngredients.html";
		}
	}

	@GetMapping("/admin/addIngredients/{id}")
	public String adminManageIngredients(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
		model.addAttribute("ingredient", new Ingredient());
		model.addAttribute("recipe", this.recipeservice.findById(id));
		model.addAttribute("allIngredients", this.ingredientService.findAll());
		model.addAttribute("recipeIngredients", this.recipeservice.findById(id).getRecipeIngredients());
		return "/admin/adminIngredients.html";
	}
	
	@PostMapping("/admin/newIngredient")
	public String adminNewIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
		this.ingredientValidator.validate(ingredient, bindingResult);
		if (!bindingResult.hasErrors()) { 
			this.ingredientService.save(ingredient);
			}
		return "redirect:/admin/addIngredients/" + id; //forzo il refresh della pagina
	}
	
	@PostMapping("/admin/updateIngredients/{id}")
	public String adminUpdateIngredient(@PathVariable("id") Long id, @ModelAttribute("ingredient") Ingredient ingredient, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) { 
			this.ingredientService.save(ingredient);
			return "redirect:updateIngredients/" + id; //forzo il refresh della pagina
		} else {
			return "admin.html";
		}
	}



}
