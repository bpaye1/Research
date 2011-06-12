package org.research.pet.model;

import java.util.List;


public class PetViewModel {

	private String message;
	private List<SelectItem> petTypes;
	private List<SelectItem> petMoods;
	private List<PetModel> pets;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<SelectItem> getPetTypes() {
		return petTypes;
	}
	
	public void setPetTypes(List<SelectItem> petTypes) {
		this.petTypes = petTypes;
	}
	
	public List<SelectItem> getPetMoods() {
		return petMoods;
	}
	public void setPetMoods(List<SelectItem> petMoods) {
		this.petMoods = petMoods;
	}
	public List<PetModel> getPets() {
		return pets;
	}
	
	public void setPets(List<PetModel> pets) {
		this.pets = pets;
	}
}
