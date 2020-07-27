package com.intiformation.gestionbanque.dao;

import com.intiformation.gestionbanque.modele.CompteCourant;

/**
 * Définition de l'interface DAO pour les comptes courants
 * @author gabri
 *
 */
public interface ICompteCourantDAO extends IGestion<CompteCourant>{

	// méthode pour chercher un compte par l'id de son propriétaire
	public CompteCourant getByOwnerId(Integer id );
	
	public boolean linkToOwner(Integer num_compte,Integer idClient);
	
}// end interface
