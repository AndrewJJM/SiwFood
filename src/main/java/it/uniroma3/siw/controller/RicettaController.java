package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {
	  @Autowired RicettaService ricettaService;
	  
	  @GetMapping("/nuovaRicetta")
	  public String formNewRicetta(Model model) {
		  model.addAttribute("ricetta", new Ricetta().getIngredienti().add(new Ingrediente()));
		  return "formNuovaRicetta.html";
	  }
	  
	  @PostMapping("/ricette")
	  public String newRicetta(@ModelAttribute("ricetta") Ricetta ricetta, Model model) {
		  this.ricettaService.save(ricetta); 
		  model.addAttribute("ricetta", ricetta); //per il redirect
		  return "redirect:ricette/" + ricetta.getId(); //per evitare la doppia richiesta in caso di refresh della pagina
	  }
	  
	  @GetMapping("/ricette/{id}")
	  public String getRicetta(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("ricetta", this.ricettaService.findById(id));
		  return "ricette.html";
	  }
	  
	  @GetMapping("/ricette")
	  public String listRicette(Model model){
		  model.addAttribute("ricette", this.ricettaService.findAll());
		  return "ricette.html";
	  }

}
