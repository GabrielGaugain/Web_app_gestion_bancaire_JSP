package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.dao.CompteCourantDAOImpl;
import com.intiformation.gestionbanque.dao.ICompteCourantDAO;
import com.intiformation.gestionbanque.modele.CompteCourant;

public class CompteCourantServiceImpl implements ICompteCourantService {
	public ICompteCourantDAO compteCourantDAO;
	
	public CompteCourantServiceImpl() {
		compteCourantDAO = new CompteCourantDAOImpl();
	}
	
	
	@Override
	public boolean ajouterCompteCourant(CompteCourant pCompteCourant) {
		return compteCourantDAO.add(pCompteCourant);
	}

	@Override
	public boolean modifierCompteCourant(CompteCourant pCompteCourant) {
		return compteCourantDAO.update(pCompteCourant);
	}

	@Override
	public boolean supprimerCompteCourant(Integer pIdCompteCourant) {
		return compteCourantDAO.delete(pIdCompteCourant);
	}

	@Override
	public List<CompteCourant> findAllCompteCourants() {
		return compteCourantDAO.getAll();
	}

	@Override
	public CompteCourant findCompteCourantById(Integer pIdCompteCourant) {
		return compteCourantDAO.getById(pIdCompteCourant);
	}

	@Override
	public CompteCourant findCompteCourantByOwnerId(Integer pIdCompteCourant) {
		return compteCourantDAO.getByOwnerId(pIdCompteCourant);
	}


	@Override
	public boolean attribuerCompteCourant(Integer pIdCompteCourant, Integer idClient) {
		return compteCourantDAO.linkToOwner(pIdCompteCourant, idClient);
	}

}
