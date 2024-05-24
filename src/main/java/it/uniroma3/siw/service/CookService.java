package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cook;
import it.uniroma3.siw.repository.CookRepository;

@Service
public class CookService {
	@Autowired 
	private CookRepository cookRepository;
	
	public Cook findById(Long id) {
		return cookRepository.findById(id).get(); //operazione CRUD della Repo
	}

	public Iterable<Cook> findAll() {
		return cookRepository.findAll(); //operazione CRUD della Repo
	}

	
	public void save(Cook cook) {
		cookRepository.save(cook);
	}


}