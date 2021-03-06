package fr.demos.formation.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;

import fr.demos.formation.data.CompteDAO;
import fr.demos.formation.model.Adresse;
import fr.demos.formation.model.Compte;
import fr.demos.formation.model.ComptePremium;


@Controller
@SessionAttributes({"compte"})
public class CompteController {
	
	@Autowired
	private LocaleResolver sessionLocalResolver;
	 
	@Autowired
	private CompteDAO compteDao;
	
	public CompteDAO getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDAO compteDao) {
		this.compteDao = compteDao;
	}

	@RequestMapping(value = "/saisieCompte.htm", method = RequestMethod.GET)
	public String affichePage(ModelMap model) {
		model.addAttribute("compte", new Compte());
		return "saisieCompte";
	}

	@RequestMapping(value = "/enregistrerCompte.htm", method = RequestMethod.POST)
	public String enregistrerCompte(@ModelAttribute("compte") @Valid Compte compte, BindingResult result) {
		
		System.out.println(Locale.getDefault());
		if (result.hasErrors()) {
			//sessionLocalResolver.setLocale(request, response, Locale.ENGLISH);
			System.out.println("in english please !");
			return "saisieCompte";
		} else {
			System.out.println("le nom : " + compte.getNom());
			System.out.println("le prenom : " + compte.getPrenom());
			System.out.println("l age : " + compte.getAge());
			
			try {
				compteDao.insert(compte);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return "saisieSuccess";
		}
		
	}
	
	
	@RequestMapping(value = "/validerCompteAdresse.htm", method = RequestMethod.POST)
	public String validerCompteAdresse(ModelMap model, @ModelAttribute("compte") @Valid Compte compte, HttpSession session, BindingResult result) {
		
		System.out.println(Locale.getDefault());
		if (result.hasErrors()) {
			//sessionLocalResolver.setLocale(request, response, Locale.ENGLISH);
			System.out.println("in english please !");
			return "saisieCompte";
		} else {
			System.out.println("le nom : " + compte.getNom());
			System.out.println("le prenom : " + compte.getPrenom());
			System.out.println("l age : " + compte.getAge());
			
			Compte c = compte;
			session.setAttribute("compte", c);
			System.out.println("saisieAdresse ici !");
			
			String retour = affichePageAdresse(model);
			
			return retour;
		}
		
	}
	
	@RequestMapping(value = "/saisieAdresse.htm", method = RequestMethod.GET)
	public String affichePageAdresse(ModelMap model) {
		model.addAttribute("adresse", new Adresse());
		return "saisieAdresse";
	}
	
	@RequestMapping(value = "/validerAdresse.htm", method = RequestMethod.POST)
	public String validerAdresse(ModelMap model, @ModelAttribute("adresse") @Valid Adresse adresse, HttpSession session, BindingResult result) {
			
		if (result.hasErrors()) {			
			return "saisieCompte";
		} else {
			
			Compte c = (Compte) session.getAttribute("compte");
			System.out.println("adresse ici !" + c);
			c.addAdresse(adresse);		
			
			try {
				compteDao.insert(c);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return "adresseSuccess";
		}
		
	}
	
	@RequestMapping(value = "/listeDesComptes.htm", method = RequestMethod.GET)
	public String listeCompte(ModelMap model) {

			List<Compte> comptes = new ArrayList();
		
			try {
				comptes = compteDao.selectAll();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			model.addAttribute("comptes", comptes);
			
			return "listeComptes";
				
	}
	
	
	@RequestMapping(value = "/rechercherCompteParId.htm", method = RequestMethod.GET)
	public String rechercheCompte(ModelMap model, @RequestParam(name="mail") String mail) {
			// appel du find dans le dao
			// recup du Compte, on le stocke dans le model
			Compte compte = new Compte();
		
			try {
				compte = compteDao.select(mail);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			if(compte != null)
				model.addAttribute("compte", compte);
			else {
				model.addAttribute("compte", new Compte());
			}
			return "saisieCompte";
				
	}
	
	@RequestMapping(value = "/adresses.htm", method = RequestMethod.GET)
	public void essaiHibernate() {
		
		Compte c = new Compte("vv@free.fr", "vv", "vv", LocalDateTime.now().toLocalDate());
		Adresse a1 = new Adresse("2 rue napoleon", 75015, "Paris", "France");
		Adresse a2 = new Adresse("3 rue bonaparte", 92400, "Courbevoie", "France");
	
		c.addAdresse(a1);
		c.addAdresse(a2);
	
		ComptePremium cp = new ComptePremium("bb@free.fr", "bb", "bb", LocalDateTime.now().toLocalDate(), 10, 1, LocalDateTime.now().toLocalDate());
		
		try {
			compteDao.insert(c);			
			compteDao.insert(cp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	@RequestMapping(value = "/heritage.htm", method = RequestMethod.GET)
	public String essaiHeritage(ModelMap model) {
		Compte c = new Compte("cc@free.fr", "cc", "cc", LocalDateTime.now().toLocalDate());	
		Compte c2 = new Compte("dd@free.fr", "dd", "dd", LocalDateTime.now().toLocalDate());	
		Adresse a1 = new Adresse("2 rue napoleon", 75015, "Paris", "France");
		Adresse a2 = new Adresse("3 rue bonaparte", 92400, "Courbevoie", "France");
		
		ComptePremium cp = new ComptePremium("bb@free.fr", "bb", "bb", LocalDateTime.now().toLocalDate(), 10, 1, LocalDateTime.now().toLocalDate());
		cp.addFilleul(c);
		cp.addFilleul(c2);
		
		try {						
			compteDao.insert(cp);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		model.addAttribute("compte", cp);
		
		return "creationCompte";
	}
	
	@RequestMapping(value = "/english.htm", method = RequestMethod.GET)
	public String english(HttpServletRequest request, HttpServletResponse response) {
		sessionLocalResolver.setLocale(request, response, Locale.ENGLISH);
		System.out.println(sessionLocalResolver.toString());
		System.out.println(LocaleContextHolder.getLocale());
		return "saisieCompte";
	}

	@RequestMapping(value = "/french.htm", method = RequestMethod.GET)
	public String french(HttpServletRequest request, HttpServletResponse response) {
		sessionLocalResolver.setLocale(request, response, Locale.FRENCH);
		return "saisieCompte";
	}

}
