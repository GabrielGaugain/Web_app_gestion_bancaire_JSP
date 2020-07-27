package com.intiformation.gestionbanque.dao;

import com.intiformation.gestionbanque.modele.CompteCourant;

/**
 * D�finition de l'interface DAO pour les comptes courants
 * @author gabri
 *
 */
public interface ICompteCourantDAO extends IGestion<CompteCourant>{

	// m�thode pour chercher un compte par l'id de son propri�taire
	public CompteCourant getByOwnerId(Integer id );
	
	public boolean linkToOwner(Integer num_compte,Integer idClient);
	
}// end interface
