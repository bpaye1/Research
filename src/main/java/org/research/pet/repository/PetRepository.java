package org.research.pet.repository;

import java.util.List;

import org.research.pet.domain.Pet;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;

public interface PetRepository {
	List<PetModel> matching(PetSearchBuilder searchBuilder);
	Pet find(Integer Number);
	void add(Pet pet);	
	void remove(Pet pet);
	PetSearchBuilder createSearchBuider();
	
	public interface PetSearchBuilder {
		PetSearchBuilder searchByPetName(String name);
		PetSearchBuilder searchByPetNumber(Integer number);
		PetSearchBuilder searchByPetMood(PetMood mood);
		PetSearchBuilder searchByPetType(PetType type);
	}
}
