package com.intiformation.gestionbanque.dao;

import com.intiformation.gestionbanque.modele.CompteEpargne;

/**
 * D�finition de l'interface DAO pour les comptes Epargnes
 * @author gabri
 *
 */
public interface ICompteEpargneDAO extends IGestion<CompteEpargne>{

	// m�thode pour chercher un compte par l'id de son propri�taire
	public CompteEpargne getByOwnerId(Integer id );
	
	public boolean linkToOwner(Integer num_compte,Integer idClient);
	
	
}// end interface
