package fr.demos.formation.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
				
		em.merge(a);

	}

	@Override
	@Transactional(readOnly=true)
	public List<Compte> selectAll() {
		
		String query = "select c from Compte c ";
		
		TypedQuery<Compte> q = em.createQuery(query, Compte.class);
			
		List<Compte> comptes = q.getResultList();
		
		return comptes;
	}

	@Override
	@Transactional(readOnly=true)
	public Compte select(String mail) {
		
		Compte c = em.find(Compte.class, mail);
		
		return c;
	}
	
	
}
