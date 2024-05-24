package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ingredient;
import it.uniroma3.siw.repository.IngredientRepository;

@Service
public class IngredientService {
	@Autowired 
	private IngredientRepository ingredientRepository;
	
	public Ingredient findById(Long id) {
		return ingredientRepository.findById(id).get(); //operazione CRUD della Repo
	}

	public Iterable<Ingredient> findAll() {
		return ingredientRepository.findAll(); //operazione CRUD della Repo
	}

	
	public void save(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}


}
