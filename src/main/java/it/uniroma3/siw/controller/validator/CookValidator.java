package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cook;
import it.uniroma3.siw.repository.CookRepository;

@Component
public class CookValidator implements Validator{
	@Autowired
	CookRepository cookRepository;
	
	@Override
	public void validate(Object o, Errors errors) {
		Cook cook = (Cook) o;
		if(cook.getName()!=null && cook.getSurname()!=null
			&& cookRepository.existsByNameAndSurnameAndDateOfBirth(cook.getName(), cook.getSurname(), cook.getDateOfBirth())) {
			errors.reject("cook.duplicate");
		}
	}
	
	/* sezione standard di codice che indica che sto 
	 * lavorando sulla classe Recipe */
	@Override
	public boolean supports(Class<?> aClass) {
		return Cook.class.equals(aClass);
	}

}
