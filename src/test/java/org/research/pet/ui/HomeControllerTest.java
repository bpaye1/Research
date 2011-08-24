package org.research.pet.ui;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.research.pet.domain.Pet;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.model.PetViewModel;
import org.research.pet.repository.PetRepository;
import org.research.pet.repository.PetRepository.PetSearchBuilder;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	@Mock
	PetRepository repository;
	
	@Test
	public void initializeShouldReturnPetViewModelWithSelectItemListOfPetMoods() throws Exception {
		HomeController controller = new HomeController();
		PetViewModel model = controller.initialize();
		assertThat(model.getPetMoods(), hasItem(newSelectItem(PetMood.ANGRY)));
		assertThat(model.getPetMoods(), hasItem(newSelectItem(PetMood.HAPPY)));
		assertThat(model.getPetMoods(), hasItem(newSelectItem(PetMood.INDIFFERENT)));
	}
	
	@Test
	public void searchAll() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = mock(PetModel.class);
		PetSearchBuilder searchBuilder = mock(PetSearchBuilder.class);
		when(repository.createSearchBuider()).thenReturn(searchBuilder);
		controller.search(model);
		verify(repository).createSearchBuider();
		verify(repository).matching(searchBuilder);	
	}
	
	@Test
	public void searchByAllCriterias() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = new PetModel(PetType.BIRD, 10001, "Birdie", PetMood.HAPPY);
		PetSearchBuilder searchBuilder = mock(PetSearchBuilder.class);
		when(repository.createSearchBuider()).thenReturn(searchBuilder);
		controller.search(model);
		verify(repository).createSearchBuider();
		verify(searchBuilder).searchByPetType(model.getType());
		verify(searchBuilder).searchByPetName(model.getName());
		verify(searchBuilder).searchByPetNumber(model.getNumber());
		verify(searchBuilder).searchByPetMood(model.getMood());
		verify(repository).matching(searchBuilder);	
	}
	
	@Test
	public void addPet() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = new PetModel(PetType.BIRD, 10001, "Birdie", PetMood.HAPPY);
			controller.addPet(model);
		ArgumentCaptor<Pet> argument = ArgumentCaptor.forClass(Pet.class);
		verify(repository).add(argument.capture());
		assertThat(argument.getValue().getType(), is(model.getType()));
		assertThat(argument.getValue().getNumber(), is(model.getNumber()));
		assertThat(argument.getValue().getName(), is(model.getName()));
		assertThat(argument.getValue().getMood(), is(model.getMood()));
	}
	
	@Test
	public void editPet() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = new PetModel(PetType.DOG, 10001, "Birdie", PetMood.ANGRY);
		Pet pet = mock(Pet.class);
		when(repository.find(model.getNumber())).thenReturn(pet);
		controller.editPet(model);
		verify(repository).find(model.getNumber());
		verify(pet).setType(model.getType());
		verify(pet).setName(model.getName());
		verify(pet).setMood(model.getMood());
	}
	
	@Test
	public void removePet() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = new PetModel(PetType.DOG, 10001, "Birdie", PetMood.ANGRY);
		Pet pet = mock(Pet.class);
		when(repository.find(model.getNumber())).thenReturn(pet);
		controller.removePet(model);
		verify(repository).find(model.getNumber());
		verify(repository).remove(pet);
	}
	
	private SelectItem newSelectItem(PetMood petMood){
		return new SelectItem(petMood.name(), petMood.getCode());
	}
}
