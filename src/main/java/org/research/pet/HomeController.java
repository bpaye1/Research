package org.research.pet;

import java.util.List;

import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.model.PetViewModel;
import org.research.pet.model.SelectItem;
import org.research.pet.repository.PetRepository;
import org.research.pet.repository.PetRepository.PetSearchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

/**
 * Handles requests for the application home page.
 */
@Transactional
@Controller
public class HomeController {
	
	@Autowired
	PetRepository repository;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/initialize", method=RequestMethod.GET)
	public @ResponseBody PetViewModel getInitialPageData(){
		
		List<SelectItem> petTypes = Lists.newArrayList();
		petTypes.add(new SelectItem(String.valueOf(PetType.CAT), PetType.CAT.getCode()));
		petTypes.add(new SelectItem(String.valueOf(PetType.DOG), PetType.DOG.getCode()));
		petTypes.add(new SelectItem(String.valueOf(PetType.BIRD), PetType.BIRD.getCode()));
		petTypes.add(new SelectItem(String.valueOf(PetType.FISH), PetType.FISH.getCode()));
		
		List<SelectItem> petMoods = Lists.newArrayList();
		petMoods.add(new SelectItem(String.valueOf(PetMood.JUMPING_OFF_THE_WALLS), PetMood.JUMPING_OFF_THE_WALLS.getCode()));
		petMoods.add(new SelectItem(String.valueOf(PetMood.HAPPY), PetMood.HAPPY.getCode()));
		petMoods.add(new SelectItem(String.valueOf(PetMood.SAD), PetMood.SAD.getCode()));
		petMoods.add(new SelectItem(String.valueOf(PetMood.ANGRY), PetMood.ANGRY.getCode()));
		petMoods.add(new SelectItem(String.valueOf(PetMood.INDIFFERENT), PetMood.INDIFFERENT.getCode()));
		
		PetViewModel model = new PetViewModel();
		model.setPetTypes(petTypes);
		model.setPetMoods(petMoods);
		return model;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody PetViewModel getData(){
//		List<PetModel> pets = Lists.newArrayList();
//		pets.add(new PetModel(PetType.CAT, 1, "Garfield",PetMood.INDIFFERENT));
//		pets.add(new PetModel(PetType.CAT, 2, "Tom", PetMood.ANGRY));
//		pets.add(new PetModel(PetType.CAT, 3, "Felix", PetMood.INDIFFERENT));
//		pets.add(new PetModel(PetType.DOG, 4, "Bailey", PetMood.JUMPING_OFF_THE_WALLS));
//		pets.add(new PetModel(PetType.DOG, 5, "Rex", PetMood.ANGRY));
//		pets.add(new PetModel(PetType.DOG, 6, "Bobo", PetMood.HAPPY));
//		pets.add(new PetModel(PetType.DOG, 7, "Pooch", PetMood.SAD));
//		pets.add(new PetModel(PetType.BIRD, 8, "Tweety", PetMood.HAPPY));
//		pets.add(new PetModel(PetType.BIRD, 9, "Coocoo", PetMood.JUMPING_OFF_THE_WALLS));
//		pets.add(new PetModel(PetType.FISH, 9, "George", PetMood.ANGRY));
		
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		
		List<PetModel> pets = repository.matching(searchBuilder);
		//List<PetModel> pets = repository.all();
		
		PetViewModel model = new PetViewModel();
		model.setPets(pets);
		return model;
	}
}

