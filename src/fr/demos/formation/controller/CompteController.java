package fr.demos.formation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.LocaleResolver;

import fr.demos.formation.data.CompteDAO;
import fr.demos.formation.model.Compte;


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
	
	@RequestMapping(value = "/listeDesComptes.htm", method = RequestMethod.GET)
	public String listeCompte(ModelMap model) {

			List<Compte> comptes = new ArrayList();
		
			try {
				comptes = compteDao.select();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			model.addAttribute("comptes", comptes);
			
			return "listeComptes";
				
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
