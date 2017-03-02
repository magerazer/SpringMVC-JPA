package fr.demos.formation.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="comptePremium")
@DiscriminatorValue("premium")
public class ComptePremium extends Compte {

	private int pourcentageReduc;
	private int niveauPremium;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dateFin;
	
	
	@JoinColumn(name="compteId")
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.MERGE,
			CascadeType.REMOVE})
	private List<Compte> filleuls = new ArrayList<Compte>();
	
	public ComptePremium() {
		super();
		
		
	}

	public ComptePremium(String mail, String nom, String prenom, LocalDate anneeNaissance, int pourcentageReduc, int niveauPremium, LocalDate dateFin) {
		super(mail, nom, prenom, anneeNaissance);
		this.pourcentageReduc = pourcentageReduc;	
		this.niveauPremium = niveauPremium;
		this.dateFin = dateFin;
	}
	
	public void addFilleul(Compte c) {
		this.filleuls.add(c);
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
