package org.research.pet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.research.pet.domain.PetMood.PetMoodUserType;
import org.research.pet.domain.PetType.PetTypeUserType;

@Entity
@Table(name="PET")
@TypeDefs({@TypeDef(name="petMood", typeClass=PetMoodUserType.class),
	@TypeDef(name="petType", typeClass=PetTypeUserType.class)})
public class Pet {

    @Id
    @GeneratedValue 
    @Column(name="PET_ID")
    private Long id;
    
    @Type(type="petType")
    @Column(name="PET_TYPE")
	private PetType type;
    
    @Column(name="PET_NUMBER")
	private Integer number;
    
    @Column(name="PET_NAME")
	private String name;
    
    @Type(type="petMood")
    @Column(name="PET_MOOD")
	private PetMood mood;
	
    @Deprecated
	public Pet(){
    	//Used for persistence only.
	}
	
	public Pet(PetType type, Integer number, String name, PetMood mood) {
		this.type = type;
		this.number = number;
		this.name = name;
		this.mood = mood;
	}

	public Long getId() {
		return id;
	}

	public PetType getType() {
		return type;
	}

	public Integer getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public PetMood getMood() {
		return mood;
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
		Pet other = (Pet) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
