package org.research.brand.models;

public class Brand {

	
	public enum BrandType{PRIVATE, NATIONAL, EXCLUSIVE};
	public enum BrandStatusCode{ACTIVE, INACTIVE};
	
	private BrandType type;
	private Integer number;
	private String name;
	private BrandStatusCode status;
	
	public Brand(BrandType type, Integer number, String name,
			BrandStatusCode status) {
		super();
		this.type = type;
		this.number = number;
		this.name = name;
		this.status = status;
	}

	public BrandType getType() {
		return type;
	}

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public BrandStatusCode getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brand other = (Brand) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
