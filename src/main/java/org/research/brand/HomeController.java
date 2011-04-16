package org.research.brand;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		return "home";
	}
	
	@RequestMapping(value="/initialize", method=RequestMethod.GET)
	public @ResponseBody BrandViewModel getInitialPageDate(){
		System.out.println("In Controller Method !!!!");
		
		List<SelectItem> brandTypes = Lists.newArrayList();
		brandTypes.add(new SelectItem("N", "National"));
		brandTypes.add(new SelectItem("P", "Private"));
		brandTypes.add(new SelectItem("E", "Exclusive"));
		
		List<SelectItem> statusCodes = Lists.newArrayList();
		statusCodes.add(new SelectItem("ACTV", "Active"));
		statusCodes.add(new SelectItem("INTV", "Inactive"));
		statusCodes.add(new SelectItem("OBSL", "Obsolete"));
		
		BrandViewModel model = new BrandViewModel("jQuery getJSON is working !!!!");
		model.setBrandTypes(brandTypes);
		model.setStatusCodes(statusCodes);
		return model;
	}
	
}

