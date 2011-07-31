package org.research.pet.ui;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.research.pet.domain.PetMood;
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
	public void getData() throws Exception {
		HomeController controller = new HomeController(repository);
		PetModel model = mock(PetModel.class);
		PetSearchBuilder searchBuilder = mock(PetSearchBuilder.class);
		when(repository.createSearchBuider()).thenReturn(searchBuilder);
		controller.getData(model);
		verify(repository).createSearchBuider();
		verify(repository).matching(searchBuilder);	
	}
	
	private SelectItem newSelectItem(PetMood petMood){
		return new SelectItem(petMood.name(), petMood.getCode());
	}
}
