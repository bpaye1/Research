package org.research.pet.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration({"classpath:test-application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class PetPersistenceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@PersistenceContext
	EntityManager em;
	
	@Test
	public void persistPet() throws Exception {
		Pet bailey = new Pet(PetType.DOG, 1, "Bailey", PetMood.HAPPY);
		em.persist(bailey);
		em.flush();
		em.clear();
		
		Pet persistedPet = em.find(Pet.class, bailey.getId());
		assertThat(persistedPet, is(bailey));
	}

}
