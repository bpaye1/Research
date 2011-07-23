package org.research.pet.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.hibernate.HibernateException;

import com.google.common.collect.Maps;

public enum PetMood {
	JUMPING_OFF_THE_WALLS("Jumping off the walls"),HAPPY("Happy"), SAD("Sad"), INDIFFERENT("Indifferent"), ANGRY("Angry");
	
	private final static Map<String, PetMood> cache = Maps.newHashMap();
	private String code;

	private PetMood(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	static {
		for(PetMood mood : PetMood.values()){
			cache.put(mood.code, mood);
		}
	}
	
	public static PetMood fromCode(String code){
		PetMood mood = cache.get(code);
		if(mood == null){
			throw new IllegalArgumentException(String.format("Unknown Pet Mood %s", code));
		}
		return mood;
	}
	
	
	
	public static class PetMoodUserType extends AbstractUserType{

		public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
			String returnedValue = rs.getString(names[0]);
			if(rs.wasNull()){
				return null;
			}
			return PetMood.fromCode(returnedValue);
		}

		public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
			if(value == null) {
				st.setNull(index, Types.VARCHAR);
			}
			else{
				st.setString(index, ((PetMood)value).getCode());
			}
		}

		
		public Class returnedClass() {
			return PetMood.class;
		}
	}
}
