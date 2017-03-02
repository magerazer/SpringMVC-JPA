package fr.demos.formation.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="compteAbonne")
@DiscriminatorValue("abonne")
public class CompteAbonne extends Compte {

	
	private int reduc;

	
	
	
	public CompteAbonne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteAbonne(String mail, String nom, String prenom, LocalDate anneeNaissance, int reduc) {
		super(mail, nom, prenom, anneeNaissance);
		this.reduc = reduc;
	}

	public int getReduc() {
		return reduc;
	}

	public void setReduc(int reduc) {
		this.reduc = reduc;
	}

	
	
	
}

