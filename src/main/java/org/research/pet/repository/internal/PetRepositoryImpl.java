package org.research.pet.repository.internal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.research.pet.domain.Pet;
import org.research.pet.domain.PetMood;
import org.research.pet.domain.PetType;
import org.research.pet.model.PetModel;
import org.research.pet.repository.PetRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Transactional
@Repository
public class PetRepositoryImpl implements PetRepository {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<PetModel> all(){
		String queryString = "select new org.research.pet.model.PetModel(p.type, p.number, p.name, p.mood)" +
			"from Pet p";
		Query query = em.createQuery(queryString);
		return query.getResultList();
 }
	
	public Pet find(Long id) {
		return em.find(Pet.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<PetModel> matching(PetSearchBuilder searchBuilder) {	
		List<Pet> pets = searchBuilder.build().list();
		List<PetModel> models = Lists.newArrayList();
		for (Pet pet : pets){
			models.add(new PetModel(pet.getType(), pet.getNumber(), pet.getName(), pet.getMood()));
		}
		return models;
	}

	public void add(Pet pet) {
		em.persist(pet);
		em.flush();
		em.clear();
	}

	public void remove(Pet pet) {
		em.remove(pet);
		em.flush();	
		em.clear();
	}

	public PetSearchBuilder createSearchBuider() {
		Criteria criteria = ((Session)em.getDelegate()).createCriteria(Pet.class);
		return new PetSearchBuilderImpl(criteria);
	}
	
	public class PetSearchBuilderImpl implements PetSearchBuilder {

		Criteria criteria;
		
		PetSearchBuilderImpl(Criteria criteria){
			this.criteria = criteria;
		}
		
		public PetSearchBuilder searchByPetName(String name){
			criteria.add(Restrictions.eq("name", name));
			return this;
		}
		
		public PetSearchBuilder searchByPetNumber(Integer number){
			criteria.add(Restrictions.eq("number", number));
			return this;
		}
		
		public PetSearchBuilder searchByPetMood(PetMood mood){
			criteria.add(Restrictions.eq("mood", mood));
			return this;
		}
		
		public PetSearchBuilder searchByPetType(PetType type){
			criteria.add(Restrictions.eq("type", type));
			return this;
		}
		
		public Criteria build(){
			criteria.addOrder(Order.asc("name"));
			criteria.setProjection(null);
			criteria.setResultTransformer(Criteria.ROOT_ENTITY);
			return criteria;
		}

	}
}
