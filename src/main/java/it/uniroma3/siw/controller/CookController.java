package it.uniroma3.siw.controller;

import java.io.IOException;

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

import it.uniroma3.siw.controller.validator.CookValidator;
import it.uniroma3.siw.model.Cook;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.service.CookService;
import jakarta.validation.Valid;

@Controller
public class CookController {
	  @Autowired CookService cookService;
	  @Autowired CookValidator cookValidator;
	  @Autowired ImageRepository imageRepository;
	  
	  @GetMapping("/admin/newCook")
	  public String formNewCook(Model model) {
		  model.addAttribute("cook", new Cook());
		  return "formNewCook.html";
	  }
	  
	  @PostMapping("/admin/cooks")
	  public String newCook(@Valid @ModelAttribute("cook") Cook cook, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile image) throws IOException {
		  this.cookValidator.validate(cook, bindingResult);
		  if (!bindingResult.hasErrors()) {
			  Image img = new Image(image.getBytes());
			  this.imageRepository.save(img);
			  cook.setImage(img);
			  this.cookService.save(cook);
			  model.addAttribute("cook", cook); //per il redirect
			  return "redirect:/cooks/" + cook.getId(); //per evitare la doppia richiesta in caso di refresh della pagina
			  } else {
			  return "formNewCook.html";
		  }
	  }
	  
	  @GetMapping("/cooks/{id}")
	  public String getCook(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("cook", this.cookService.findById(id));
		  return "cook.html";
	  }
	  
	  @GetMapping("/cooks")
	  public String listCooks(Model model){
		  model.addAttribute("cooks", this.cookService.findAll());
		  return "cooks.html";  
	  }
}
