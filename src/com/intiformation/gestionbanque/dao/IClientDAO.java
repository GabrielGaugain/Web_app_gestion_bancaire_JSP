package com.intiformation.gestionbanque.dao;
import java.util.List;

import com.intiformation.gestionbanque.modele.Client;

public interface IClientDAO extends IGestion<Client>{

	/**
	 * Recup de tous les clients d'un conseiller via la clé étrangère de la table 'clients', 'id_conseiller'
	 * @param id_conseiller
	 * @return List<Client> : associée au conseiller
	 */
	public List<Client> getConseillerAllClients(int id_conseiller);
	
}// end interface
