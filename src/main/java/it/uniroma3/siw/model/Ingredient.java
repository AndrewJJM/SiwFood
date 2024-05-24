package it.uniroma3.siw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ingredient {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String name;
	private Integer quantity;
    private String measurement_unit;
    
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantita) {
		this.quantity = quantita;
	}
	public String getMeasurement_unit() {
		return measurement_unit;
	}
	public void setMeasurement_unit(String unita_misura) {
		this.measurement_unit = unita_misura;
	}
    

}
