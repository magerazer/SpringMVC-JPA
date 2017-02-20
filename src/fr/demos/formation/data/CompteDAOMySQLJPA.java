package fr.demos.formation.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.demos.formation.model.Compte;

@Repository
public class CompteDAOMySQLJPA implements CompteDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void insert(Compte a) throws Exception {
				
		em.persist(a);

	}

	@Override
	public List<Compte> select() {
		
		
		
		return null;
	}

}
