package fr.demos.formation.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="comptePremium")
@DiscriminatorValue("premium")
public class ComptePremium extends Compte {

	private int pourcentageReduc;
	private int niveauPremium;
	private LocalDate dateFin;
	private List<Compte> filleuls;
	
	public ComptePremium() {
		super();
		
		
	}

	public ComptePremium(String mail, String nom, String prenom, LocalDate anneeNaissance) {
		super(mail, nom, prenom, anneeNaissance);
		
		
		
	}
	
	

	public int getPourcentageReduc() {
		return pourcentageReduc;
	}

	public void setPourcentageReduc(int pourcentageReduc) {
		this.pourcentageReduc = pourcentageReduc;
	}

	public int getNiveauPremium() {
		return niveauPremium;
	}

	public void setNiveauPremium(int niveauPremium) {
		this.niveauPremium = niveauPremium;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public List<Compte> getFilleuls() {
		return filleuls;
	}

	public void setFilleuls(List<Compte> filleuls) {
		this.filleuls = filleuls;
	}

	@Override
	public String toString() {
		return "ComptePremium [pourcentageReduc=" + pourcentageReduc + ", niveauPremium=" + niveauPremium + ", dateFin="
				+ dateFin + ", filleuls=" + filleuls + "]";
	}

	
	
	
	
	
}
