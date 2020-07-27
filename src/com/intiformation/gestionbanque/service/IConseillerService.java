package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;

/**
 * interface de base de la couche service pour les conseillers. <br/>
 * la couche service permet d'exposer les fonctionnalités de l'application <br/>
 * la coucher service permet de traiter les données de la DAO avant l'affichage
 * deans la couche présentation
 * @author gabri
 *
 */
public interface IConseillerService {
	
	public boolean ajouterConseiller(Conseiller pConseiller);
	
	public boolean modifierConseiller(Conseiller pConseiller);
	
	public boolean supprimerConseiller(Integer pIdConseiller);
	
	public List<Conseiller> findAllConseillers();

	public Conseiller findConseillerById(Integer pIdConseiller);
		
	public boolean isConseillerExists(String pId, String pMdp);
	
	public int findIdConseillerByLogin(String pIdentifiant, String pMotDePasse);
	
	//public List<Client> findAllClientsByConseiller(int id_conseiller);

	public List<Conseiller> findAllConseillerByAdmin(int id_admin);
}
