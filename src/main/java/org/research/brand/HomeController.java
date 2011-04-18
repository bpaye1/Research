package org.research.brand;

import java.util.List;

import org.research.brand.models.Brand;
import org.research.brand.models.Brand.BrandStatusCode;
import org.research.brand.models.Brand.BrandType;
import org.research.brand.models.BrandViewModel;
import org.research.brand.models.SelectItem;
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
	public @ResponseBody BrandViewModel getInitialPageData(){
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
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public @ResponseBody BrandViewModel getData(){
		List<Brand> brands = Lists.newArrayList();
		brands.add(new Brand(BrandType.PRIVATE, 1, "My Own TVs", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.PRIVATE, 2, "My Own DVDs", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.PRIVATE, 3, "My Own Books", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 4, "Turtle", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 5, "Dog", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 6, "Cat", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 7, "Bird", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 8, "Highway 5", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 9, "Collin", BrandStatusCode.ACTIVE));
		brands.add(new Brand(BrandType.NATIONAL, 10, "Dallas", BrandStatusCode.ACTIVE));
		BrandViewModel model = new BrandViewModel("jQuery getJSON is working !!!!");
		model.setBrands(brands);
		return model;
	}
}

