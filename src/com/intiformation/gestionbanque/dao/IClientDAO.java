package com.intiformation.gestionbanque.dao;
import java.util.List;

import com.intiformation.gestionbanque.modele.Client;

public interface IClientDAO extends IGestion<Client>{

	/**
	 * Recup de tous les clients d'un conseiller via la cl� �trang�re de la table 'clients', 'id_conseiller'
	 * @param id_conseiller
	 * @return List<Client> : associ�e au conseiller
	 */
	public List<Client> getConseillerAllClients(int id_conseiller);
	
}// end interface
