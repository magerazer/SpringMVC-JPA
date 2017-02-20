package fr.demos.formation.data;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import fr.demos.formation.model.Compte;


public interface CompteDAO {

	@Transactional(rollbackFor={Exception.class})
	void insert (Compte a) throws Exception;

	List<Compte> select();
	
}
