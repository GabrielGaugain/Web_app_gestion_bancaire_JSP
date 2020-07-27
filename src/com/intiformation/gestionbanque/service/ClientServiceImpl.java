package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.dao.ClientDAOImpl;
import com.intiformation.gestionbanque.dao.IClientDAO;
import com.intiformation.gestionbanque.modele.Client;

public class ClientServiceImpl implements IClientService{
	
	private IClientDAO clientDAO;
	
	public ClientServiceImpl() {
		clientDAO = new ClientDAOImpl();
	}// end ctor

	@Override
	public boolean ajouterClient(Client pClient) {
		return clientDAO.add(pClient);
	}

	@Override
	public boolean modifierClient(Client pClient) {
		return clientDAO.update(pClient);
	}

	@Override
	public boolean supprimerClient(Integer pIdClient) {
		return clientDAO.delete(pIdClient);
	}

	@Override
	public List<Client> findAllClients() {
		return clientDAO.getAll();
	}

	@Override
	public Client findClientById(Integer pIdClient) {
		return clientDAO.getById(pIdClient) ;
	}

	@Override
	public List<Client> findAllClientsByConseiller(int id_conseiller) {
		return clientDAO.getConseillerAllClients(id_conseiller);
	}
	
	

}
