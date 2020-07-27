package com.intiformation.gestionbanque.service;

import java.util.List;
import com.intiformation.gestionbanque.modele.Client;


public interface IClientService {

	public boolean ajouterClient(Client pClient);
	
	public boolean modifierClient(Client pClient);
	
	public boolean supprimerClient(Integer pIdClient);
	
	public List<Client> findAllClients();

	public Client findClientById(Integer pIdClient);

	public List<Client> findAllClientsByConseiller(int id_conseiller);
	
}
