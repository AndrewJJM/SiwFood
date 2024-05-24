package it.uniroma3.siw.model;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private Set<String> images;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER) //forse conviene direttamente CascadeType.ALL?
    @JoinColumn(name="recipes_id") //per evitare la creazione di una tabella di join inutile
    private Set<Ingredient> ingredients;
    
    @Column(length=2000)
    private String description;
    
    @ManyToOne //qui no cascade perché imponiamo scelta tra cuochi esistenti(Forse Cascade Refresh?)
    private Cook cook;
    
    /********************************************************Methods******************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public Set<String> getImages() {
		return images;
	}

	public void setImages(Set<String> immagini) {
		this.images = immagini;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredienti) {
		this.ingredients = ingredienti;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descrizione) {
		this.description = descrizione;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}
    
}