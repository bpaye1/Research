package org.research.pet.repository.internal;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.research.pet.domain.Pet;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.repository.PetRepository;
import org.research.pet.repository.PetRepository.PetSearchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration({"classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class PetRepositoryImplTest extends AbstractTransactionalJUnit4SpringContextTests {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	PetRepository repository;
	
	private Pet bailey = new Pet(PetType.DOG, 1, "bailey", PetMood.HAPPY);
	private Pet pitou = new Pet(PetType.DOG, 2, "pitou", PetMood.INDIFFERENT);
	private Pet garfield = new Pet(PetType.CAT, 3, "garfield", PetMood.INDIFFERENT);
	
	@Test
	public void findMatchingPetTypeCriteria() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder.searchByPetType(PetType.DOG);
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets, hasItem(model(bailey)));
		assertThat(pets, hasItem(model(pitou)));
		assertThat(pets, not(hasItem(model(garfield))));
	}
	
	@Test
	public void findMatchingCriteriaPetNameCriteria() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder.searchByPetName(bailey.getName());
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets, hasItem(model(bailey)));
		assertThat(pets, not(hasItem(model(pitou))));
		assertThat(pets, not(hasItem(model(garfield))));
	}
	
	@Test
	public void findMatchingCriteriaPetNumber() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder.searchByPetNumber(garfield.getNumber());
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets, hasItem(model(garfield)));
		assertThat(pets, not(hasItem(model(pitou))));
		assertThat(pets, not(hasItem(model(bailey))));
	}
	
	@Test
	public void findMatchingCriteriaPetMood() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder.searchByPetMood(PetMood.INDIFFERENT);
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets, hasItem(model(garfield)));
		assertThat(pets, hasItem(model(pitou)));
		assertThat(pets, not(hasItem(model(bailey))));
	}
	
	@Test
	public void findMatchingAllCriterias() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder
			.searchByPetMood(PetMood.INDIFFERENT)
			.searchByPetName(garfield.getName())
			.searchByPetMood(PetMood.INDIFFERENT)
			.searchByPetType(PetType.CAT);
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets, hasItem(model(garfield)));
		assertThat(pets, not(hasItem(model(pitou))));
		assertThat(pets, not(hasItem(model(bailey))));
	}
	
	@Test
	public void findMatchingAllCriteriasShouldReturnEmptyResultWhenPetTypeCriteriaIsNotMatching() throws Exception {
		setUp();
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		searchBuilder
			.searchByPetMood(PetMood.INDIFFERENT)
			.searchByPetName(garfield.getName())
			.searchByPetMood(PetMood.INDIFFERENT)
			.searchByPetType(PetType.DOG);
		List<PetModel> pets = repository.matching(searchBuilder);
		assertThat(pets.size(), is(0));
	}
	
	@Test
	public void addPet() throws Exception {
		repository.add(bailey);
		List<Map<String, Object>> result = 
			simpleJdbcTemplate.queryForList("SELECT PET_NAME, PET_NUMBER FROM PET");
		
		assertThat(result.size(), is(1));
		assertThat((String)result.get(0).get("PET_NAME"), is(bailey.getName()));
		assertThat((Integer)result.get(0).get("PET_NUMBER"), is(bailey.getNumber()));
	}
	
	@Test
	public void removePet() throws Exception {
		repository.add(bailey);
		Pet persitedBailey = repository.find(bailey.getId());
		repository.remove(persitedBailey);
		List<Map<String, Object>> result = 
			simpleJdbcTemplate.queryForList("SELECT * FROM PET");
		assertThat(result.size(), is(0));
	}
	
	private void setUp(){
		repository.add(bailey);
		repository.add(pitou);
		repository.add(garfield);
	}
	
	
	private PetModel model(Pet pet){
		return new PetModel(pet.getType(), pet.getNumber(), pet.getName(), pet.getMood()); 
	}
}
