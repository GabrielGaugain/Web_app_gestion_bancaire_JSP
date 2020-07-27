package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.dao.CompteEpargneDAOImpl;
import com.intiformation.gestionbanque.dao.ICompteEpargneDAO;
import com.intiformation.gestionbanque.modele.CompteEpargne;

public class CompteEpargneServiceImpl implements ICompteEpargneService {

	public ICompteEpargneDAO compteEpargneDAO;
	
	public CompteEpargneServiceImpl() {
		compteEpargneDAO = new CompteEpargneDAOImpl();
	}
	
	
	@Override
	public boolean ajouterCompteEpargne(CompteEpargne pCompteEpargne) {
		return compteEpargneDAO.add(pCompteEpargne);
	}

	@Override
	public boolean modifierCompteEpargne(CompteEpargne pCompteEpargne) {
		return compteEpargneDAO.update(pCompteEpargne);
	}

	@Override
	public boolean supprimerCompteEpargne(Integer pIdCompteEpargne) {
		return compteEpargneDAO.delete(pIdCompteEpargne);
	}

	@Override
	public List<CompteEpargne> findAllCompteEpargnes() {
		return compteEpargneDAO.getAll();
	}

	@Override
	public CompteEpargne findCompteEpargneById(Integer pIdCompteEpargne) {
		return compteEpargneDAO.getById(pIdCompteEpargne);
	}

	@Override
	public CompteEpargne findCompteEpargneByOwnerId(Integer pIdCompteEpargne) {
		return compteEpargneDAO.getByOwnerId(pIdCompteEpargne);
	}


	@Override
	public boolean attribuerCompteEpargne(Integer pIdCompteEpargne, Integer idClient) {
		return compteEpargneDAO.linkToOwner(pIdCompteEpargne, idClient);
	}
}
