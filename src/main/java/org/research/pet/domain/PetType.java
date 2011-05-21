package org.research.pet.domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.hibernate.HibernateException;

import com.google.common.collect.Maps;


public enum PetType {
	DOG("Dog"), CAT("Cat"), FISH("Fish"), BIRD("Bird");
	
	private final static Map<String, PetType> cache = Maps.newHashMap();
	
	private String code;
	
	public String getCode(){
		return code;
	}
	
	static {
		for(PetType type : PetType.values()){
			cache.put(type.code, type);
		}
	}
	
	public static PetType fromCode(String code){
		PetType type = cache.get(code);
		if(type == null){
			throw new IllegalArgumentException(String.format("Unknown Pet Type %s", code));
		}
		return type;
	}
	
	private PetType(String code){
		this.code = code;
	}
	
	public static class PetTypeUserType extends AbstractUserType{

		public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
			String returnedValue = rs.getString(names[0]);
			if(rs.wasNull()){
				return null;
			}
			return PetType.fromCode(returnedValue);
		}

		public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
			if(value == null) {
				st.setNull(index, Types.VARCHAR);
			}
			else{
				st.setString(index, ((PetType)value).getCode());
			}
		}

		@SuppressWarnings({ "rawtypes" })
		public Class returnedClass() {
			return PetType.class;
		}
	}
}
