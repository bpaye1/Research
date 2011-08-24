package org.research.pet.ui;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.research.pet.domain.Pet;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.model.PetViewModel;
import org.research.pet.repository.PetRepository;
import org.research.pet.repository.PetRepository.PetSearchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	private PetRepository repository;	
	
	public HomeController(){
	}
	
	@Autowired
	public HomeController(PetRepository repository){
		this.repository = repository;
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/initialize", method=RequestMethod.GET)
	public @ResponseBody PetViewModel initialize(){
		
		List<SelectItem> petTypes = Lists.newArrayList();
		for(PetType petType : PetType.values()){
			petTypes.add(new SelectItem(petType.name(), petType.getCode()));
		}
	
		List<SelectItem> petMoods = Lists.newArrayList();
		for(PetMood petMood : PetMood.values()){
			petMoods.add(new SelectItem(petMood.name(), petMood.getCode()));
		}
		
		PetViewModel model = new PetViewModel();
		model.setPetTypes(petTypes);
		model.setPetMoods(petMoods);
		return model;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody PetViewModel search(PetModel search){
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		
		if(search.getNumber() != null){
			searchBuilder.searchByPetNumber(search.getNumber());
		}
		
		if(!StringUtils.isEmpty(search.getName())){
			searchBuilder.searchByPetName(search.getName());
		}
		
		if(search.getType() != null){
			searchBuilder.searchByPetType(search.getType());
		}
		
		if(search.getMood() != null){
			searchBuilder.searchByPetMood(search.getMood());
		}	
		
		List<PetModel> pets = repository.matching(searchBuilder);
		PetViewModel model = new PetViewModel();
		model.setPets(pets);		
		return model;	
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody PetViewModel addPet(@RequestBody PetModel model){
		repository.add(new Pet(model.getType(), model.getNumber(), model.getName(), model.getMood()));
		PetViewModel viewModel = new PetViewModel();
		viewModel.setMessage("Pet " + model.getName() + " added.");
		return viewModel;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public @ResponseBody PetViewModel editPet(@RequestBody PetModel model){
		Pet pet = repository.find(model.getNumber());
		pet.setName(model.getName());
		pet.setMood(model.getMood());
		pet.setType(model.getType());
		PetViewModel viewModel = new PetViewModel();
		viewModel.setMessage("Pet " + model.getName() + " updated.");
		return viewModel;
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public @ResponseBody PetViewModel removePet(@RequestBody PetModel model){
		Pet pet = repository.find(model.getNumber());
		repository.remove(pet);
		PetViewModel viewModel = new PetViewModel();
		viewModel.setMessage("Pet " + model.getName() + " removed.");
		return viewModel;
		
	}
}

