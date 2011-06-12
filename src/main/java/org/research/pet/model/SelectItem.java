package org.research.pet.model;

public class SelectItem {
	private String value;
	private String label;
	
	public SelectItem(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	};
	
}
