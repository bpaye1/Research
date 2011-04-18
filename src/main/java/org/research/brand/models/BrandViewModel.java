package org.research.brand.models;

import java.util.List;


public class BrandViewModel {

	private String message;
	private List<SelectItem> brandTypes;
	private List<SelectItem> statusCodes;
	private List<Brand> brands;

	public BrandViewModel(String message){
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<SelectItem> getBrandTypes() {
		return brandTypes;
	}
	
	public void setBrandTypes(List<SelectItem> brandTypes) {
		this.brandTypes = brandTypes;
	}
	
	public List<SelectItem> getStatusCodes() {
		return statusCodes;
	}

	public void setStatusCodes(List<SelectItem> statusCodes) {
		this.statusCodes = statusCodes;
	}
	
	public List<Brand> getBrands() {
		return brands;
	}
	
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
}
