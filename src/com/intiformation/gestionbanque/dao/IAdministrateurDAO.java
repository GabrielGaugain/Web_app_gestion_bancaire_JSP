package com.intiformation.gestionbanque.dao;

import java.util.List;

import com.intiformation.gestionbanque.modele.Administrateur;
import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;

public interface IAdministrateurDAO extends IGestion<Administrateur> {

	
	/**
	 * verif qu'un admin existe avec un identifiant et un mdp
	 * dans la bdd
	 * @param pIdentifiant
	 * @param pMotDePasse
	 * @return true si l'admin existe
	 */
	public boolean isAdminExists(String pIdentifiant, String pMotDePasse);
	
	/**
	 * Retourne l'id correspondant au couple (identifiant,mdp) 
	 * 		!!! => couple doit être unique normalement (ici retourn le premier du ResultSet)
	 * 
	 * @param pIdentifiant
	 * @param pMotDePasse
	 * @return
	 */
	public int getIdAdminByLogin(String pIdentifiant, String pMotDePasse);
	
	/**
	 * Recup de tous les clients associé à la clé étrangère id_conseiller
	 * 	i.e. les clients d'un conseiller
	 * @param id_admin
	 * @return
	 */
	//public List<Conseiller> getConseillerAllConseiller(int id_admin);
	
}
