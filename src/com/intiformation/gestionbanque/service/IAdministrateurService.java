package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.modele.Administrateur;
import com.intiformation.gestionbanque.modele.Conseiller;

public interface IAdministrateurService {
	
	public boolean ajouterAdministrateur(Administrateur pAdministrateur);
	
	public boolean modifierAdministrateur(Administrateur pAdministrateur);
	
	public boolean supprimerAdministrateur(Integer pIdAdministrateur);
	
	public List<Administrateur> findAllAdministrateurs();

	public Administrateur findAdministrateurById(Integer pIdAdministrateur);
		
	public boolean isAdministrateurExists(String pId, String pMdp);
	
	//public List<Conseiller> findAllConseillerByAdmin(int id_admin);
	
	public int findIdAdminByLogin(String pIdentifiant, String pMotDePasse);
}
