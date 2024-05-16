package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.service.CuocoService;

@Controller
public class CuocoController {
	  @Autowired CuocoService cuocoService;
	  
	  @GetMapping("/nuovoCuoco")
	  public String formNewCuoco(Model model) {
		  model.addAttribute("cuoco", new Cuoco());
		  return "formNuovoCuoco.html";
	  }
	  
	  @PostMapping("/cuochi")
	  public String newCuoco(@ModelAttribute("cuoco") Cuoco cuoco, Model model) {
		  this.cuocoService.save(cuoco);
		  model.addAttribute("cuoco", cuoco); //per il redirect
		  return "redirect:cuochi/" + cuoco.getId(); //per evitare la doppia richiesta in caso di refresh della pagina
	  }
	  
	  @GetMapping("/cuochi/{id}")
	  public String getCuoco(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("cuoco", this.cuocoService.findById(id));
		  return "cuoco.html";
	  }
	  
	  @GetMapping("/cuochi")
	  public String listCuochi(Model model){
		  model.addAttribute("cuochi", this.cuocoService.findAll());
		  return "cuochi.html";  
	  }
}
