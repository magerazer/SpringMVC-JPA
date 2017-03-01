package fr.demos.formation.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.demos.formation.model.Adresse;
import fr.demos.formation.model.Compte;


@Repository
public class CompteDAOMySQLJPA implements CompteDAO {

	@PersistenceContext
	private EntityManager em;
	
	// annotation @Transactional g�re la transaction par AOP
	@Override	
	@Transactional
	public void insert(Compte a) throws Exception {
		// 1 : la m�thode �tant marqu�e transactionnelle, begin
		// 2 : persist place dans le contexte un nouvel objet (pas encore en base)		
		em.merge(a);
		// 3 : flush (insert dans la base) du contexte automatique avant le commit
		// 4 : la m�thode �tant marqu�e transactionnelle, commit ou rollback
	}

	@Override
	@Transactional(readOnly=true)
	public List<Compte> selectAll() {
		
		String query = "select distinct c from Compte c left join fetch c.adresses ad where ad.ville=?";
		
		TypedQuery<Compte> q = em.createQuery(query, Compte.class);
		q.setParameter(0, "Paris");
		List<Compte> liste = q.getResultList();
				
		return liste;
	}

	@Override
	@Transactional(readOnly=true)
	public Compte select(String mail) {
		
		Compte c = em.find(Compte.class, mail);
		
		return c;
	}
	
	
}
