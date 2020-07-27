package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.dao.AdministrateurDAOImpl;
import com.intiformation.gestionbanque.dao.IAdministrateurDAO;
import com.intiformation.gestionbanque.modele.Administrateur;
import com.intiformation.gestionbanque.modele.Conseiller;
import com.intiformation.gestionbanque.modele.Administrateur;

public class AdministrateurServiceImpl implements IAdministrateurService{

	private IAdministrateurDAO administrateurDAO;
	
	public AdministrateurServiceImpl() {
		administrateurDAO = new AdministrateurDAOImpl();
	}// ctor vide
	
	
	@Override
	public boolean ajouterAdministrateur(Administrateur pAdministrateur) {
		return administrateurDAO.add(pAdministrateur);
	}

	@Override
	public boolean modifierAdministrateur(Administrateur pAdministrateur) {
		return administrateurDAO.update(pAdministrateur);
	}

	@Override
	public boolean supprimerAdministrateur(Integer pIdAdministrateur) {
		return administrateurDAO.delete(pIdAdministrateur);
	}

	@Override
	public List<Administrateur> findAllAdministrateurs() {
		return administrateurDAO.getAll();
	}

	@Override
	public Administrateur findAdministrateurById(Integer pIdAdministrateur) {
		return  administrateurDAO.getById(pIdAdministrateur);
	}

	@Override
	public boolean isAdministrateurExists(String pId, String pMdp) {
		return administrateurDAO.isAdminExists(pId, pMdp);
	}


	@Override
	public int findIdAdminByLogin(String pIdentifiant, String pMotDePasse) {
		return administrateurDAO.getIdAdminByLogin(pIdentifiant, pMotDePasse);
	}



}
