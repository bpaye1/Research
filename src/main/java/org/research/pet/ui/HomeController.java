package org.research.pet.ui;

import java.util.List;

import org.h2.util.StringUtils;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.model.PetViewModel;
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
			petTypes.add(new SelectItem(petType.getCode(), petType.getCode()));
		}
	
		List<SelectItem> petMoods = Lists.newArrayList();
		for(PetMood petMood : PetMood.values()){
			petMoods.add(new SelectItem(petMood.getCode(), petMood.getCode()));
		}
		
		PetViewModel model = new PetViewModel();
		model.setPetTypes(petTypes);
		model.setPetMoods(petMoods);
		return model;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody PetViewModel getData(PetSearch petSearch){
		PetSearchBuilder searchBuilder = repository.createSearchBuider();
		
		if(petSearch.getNumber() != null){
			searchBuilder.searchByPetNumber(petSearch.getNumber());
		}
		
		if(StringUtils.isNullOrEmpty(petSearch.getName())){
			searchBuilder.searchByPetName(petSearch.getName());
		}
		
		if(petSearch.getType() != null){
			searchBuilder.searchByPetType(PetType.fromCode(petSearch.getType()));
		}
		
		if(petSearch.getMood() != null){
			searchBuilder.searchByPetMood(PetMood.fromCode(petSearch.getMood()));
		}
		
		List<PetModel> pets = repository.matching(searchBuilder);
		PetViewModel model = new PetViewModel();
		model.setPets(pets);
		return model;
	}
}

