package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.modele.CompteCourant;

public interface ICompteCourantService {

	public boolean ajouterCompteCourant(CompteCourant pCompteCourant);
	
	public boolean modifierCompteCourant(CompteCourant pCompteCourant);
	
	public boolean supprimerCompteCourant(Integer pIdCompteCourant);
	
	public List<CompteCourant> findAllCompteCourants();

	public CompteCourant findCompteCourantById(Integer pIdCompteCourant);
	
	public CompteCourant findCompteCourantByOwnerId(Integer pIdCompteCourant);
	
	public boolean attribuerCompteCourant(Integer pIdCompteCourant, Integer idClient );
}
