package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.model.Recipe;

/*Si interfaccia direttamente con il database per l'ENTITà SPECIFICA e ci fornisce operazioni CRUD (Create, Read, Update, Delete)
 *senza dover scrivere in SQL. 
 *Non è obbligatoria l'annotazione @Repository ma aiuta con la gestione delle eccezioni*/

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	/*Possibile aggiungere metodi all'interfaccia SENZA DEFINIRLI, grazie alla
	CONVENZIONE SEMANTICA: (operazione + By + attibuti (separati da And)*/

}
