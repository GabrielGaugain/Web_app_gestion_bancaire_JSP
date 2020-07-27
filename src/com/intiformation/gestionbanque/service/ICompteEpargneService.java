package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.modele.CompteEpargne;

public interface ICompteEpargneService {

	public boolean ajouterCompteEpargne(CompteEpargne pCompteEpargne);
	
	public boolean modifierCompteEpargne(CompteEpargne pCompteEpargne);
	
	public boolean supprimerCompteEpargne(Integer pIdCompteEpargne);
	
	public List<CompteEpargne> findAllCompteEpargnes();

	public CompteEpargne findCompteEpargneById(Integer pIdCompteEpargne);
	
	public CompteEpargne findCompteEpargneByOwnerId(Integer pIdCompteEpargne);
	
	public boolean attribuerCompteEpargne(Integer pIdCompteEpargne, Integer idClient );
	
}
