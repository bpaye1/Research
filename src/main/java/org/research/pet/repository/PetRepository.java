package org.research.pet.repository;

import java.util.List;

import org.research.pet.domain.Pet;
import org.research.pet.model.PetModel;
import org.research.pet.repository.internal.PetRepositoryImpl.PetSearchBuilder;

public interface PetRepository {
	List<PetModel> matching(PetSearchBuilder searchBuilder);
	Pet find(Long id);
	void add(Pet pet);	
	void remove(Pet pet);
	PetSearchBuilder createSearchBuider();
	List<PetModel> all();
}
