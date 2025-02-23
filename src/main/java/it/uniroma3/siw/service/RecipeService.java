package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cook;
import it.uniroma3.siw.model.Recipe;
import it.uniroma3.siw.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired 
	private RecipeRepository recipeRepository;
	
	public boolean existsByNameAndCook(String nome, Cook cook) {
		return this.recipeRepository.existsByNameAndCook(nome, cook);
	}

	public Recipe findById(Long id) {
		return recipeRepository.findById(id).get(); 
	}	
	
	public Iterable<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	
	public Iterable<Recipe> findByCook(Cook cook) {
		return recipeRepository.findByCook(cook);
	}

	public void save(Recipe recipe) {
		recipeRepository.save(recipe);
	}
	
	public void remove(Recipe recipe) {
		this.recipeRepository.delete(recipe); 
	}
}
