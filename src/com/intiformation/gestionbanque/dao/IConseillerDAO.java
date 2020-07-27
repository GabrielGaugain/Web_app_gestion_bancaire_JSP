package com.intiformation.gestionbanque.dao;

import java.util.List;

import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;

public interface IConseillerDAO extends IGestion<Conseiller> {
	
	/**
	 * verif qu'un conseiller existe avec un identifiant et un mdp
	 * dans la bdd
	 * @param pIdentifiant
	 * @param pMotDePasse
	 * @return true si l'utilisateur existe
	 */
	public boolean isConseillerExists(String pIdentifiant, String pMotDePasse);
	
	/**
	 * Retourne l'id correspondant au couple (identifiant,mdp) 
	 * 		!!! => couple doit être unique normalement (ici retourn le premier du ResultSet)
	 * 
	 * @param pIdentifiant
	 * @param pMotDePasse
	 * @return
	 */
	public int getIdConseillerByLogin(String pIdentifiant, String pMotDePasse);
	
	
	/**
	 * Recup de tous les clients associé à la clé étrangère id_conseiller
	 * 	i.e. les clients d'un conseiller
	 * @param id_conseiller
	 * @return
	 */
	//public List<Client> getConseillerAllClients(int id_conseiller);
	
	/**
	 * Recup de tous les conseillers associés à la clé étrangère id_admin
	 * 	i.e. les conseiller d'un administrateur
	 * @param id_conseiller
	 * @return
	 */	
	public List<Conseiller> getConseillerAllConseiller(int id_admin);
	

}
