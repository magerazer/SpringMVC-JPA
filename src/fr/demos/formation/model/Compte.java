package fr.demos.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table (name="compte")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
public class Compte {

	

	@Size(min=1, max=15)
	private String nom;
	private String prenom;
	
	@Transient	
	private int age;
	
	@Id
	@Pattern(regexp="^[A-Za-z0-9][A-Za-z0-9.-_]+@([a-zA-Z0-9._-]+\\.[a-z]{2,6})+$" 
			)
	private String mail;
	
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate anneeNaissance;
	
	
	
	@JoinColumn(name="compteId")	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.REMOVE})	
	private Collection<Adresse> adresses = new ArrayList<Adresse>();
	
	
	public Compte() {
	}
	
	public Compte(String mail, String nom, String prenom, LocalDate anneeNaissance) {
		this.mail = mail;
		this.nom = nom;
		this.prenom = prenom;
		this.anneeNaissance = anneeNaissance;
	}

	public void addAdresse(Adresse a) {
		adresses.add(a);
	}
	
	
	

	public Collection<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(Collection<Adresse> adresses) {
		this.adresses = adresses;
	}

	public LocalDate getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(LocalDate anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	@Override
	public String toString() {
		return "Compte [nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", mail=" + mail + ", anneeNaissance="
				+ anneeNaissance + ", adresses=" + adresses + "]";
	}
	
}
