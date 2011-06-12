package org.research.pet.model;

import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;

public class PetModel {

	private PetType type;
	private Integer number;
	private String name;
	private PetMood mood;
	
	public PetModel(PetType type, Integer number, String name,
			PetMood mood) {
		super();
		this.type = type;
		this.number = number;
		this.name = name;
		this.mood = mood;
	}

	public PetType getType() {
		return type;
	}

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public PetMood getMood() {
		return mood;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetModel other = (PetModel) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
