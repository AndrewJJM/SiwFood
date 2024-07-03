package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.RecipeValidator;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.service.CookService;
import it.uniroma3.siw.service.RecipeService;
import jakarta.validation.Valid;

@Controller
public class RecipeController {
	@Autowired RecipeService recipeService;
	@Autowired RecipeValidator recipeValidator;
	@Autowired CookService cookService;
	@Autowired ImageRepository imageRepository;

	@GetMapping("/user/newRecipe")
	public String formNewRecipe(Model model) {
		Recipe recipe = new Recipe();
		//recipe.setIngredients(new HashSet<Ingredient>());
		model.addAttribute("recipe", recipe);
		model.addAttribute("cooks", this.cookService.findAll());
		return "formNewRecipe.html";
	}

	@PostMapping("/user/recipes")
	public String saveNewRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, 
			BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile image) throws IOException {
		this.recipeValidator.validate(recipe, bindingResult);
		if (bindingResult.hasErrors()) { 			//il binding controlla i vincoli espliciti semplici
			return "formNewRecipe.html";
		} else {
			Image img = new Image(image.getBytes());
			this.imageRepository.save(img);
			recipe.setImage(img);
			this.recipeService.save(recipe); 
			model.addAttribute("recipe", recipe);
			return "redirect:recipes/"+recipe.getId();
		}
	}
	

	@GetMapping("/admin/newRecipe")
	public String adminNewRecipe(Model model) {
		Recipe recipe = new Recipe();
		//recipe.setIngredients(new HashSet<Ingredient>());
		model.addAttribute("recipe", recipe);
		model.addAttribute("cooks", this.cookService.findAll());
		return "/admin/formNewRecipe.html";
	}

	@PostMapping("/admin/recipes")
	public String adminSaveNewRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, 
			BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile image) throws IOException {
		this.recipeValidator.validate(recipe, bindingResult);
		if (bindingResult.hasErrors()) { 			//il binding controlla i vincoli espliciti semplici
			return "formNewRecipe.html";
		} else {
			Image img = new Image(image.getBytes());
			this.imageRepository.save(img);
			recipe.setImage(img);
			this.recipeService.save(recipe); 
			model.addAttribute("recipe", recipe);
			return "redirect:/admin/addIngredients/"+recipe.getId();
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
