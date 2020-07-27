package com.intiformation.gestionbanque.service;

import java.util.List;

import com.intiformation.gestionbanque.dao.ConseillerDAOImpl;
import com.intiformation.gestionbanque.dao.IConseillerDAO;
import com.intiformation.gestionbanque.modele.Client;
import com.intiformation.gestionbanque.modele.Conseiller;

public class ConseillerServiceImpl implements IConseillerService{

	private IConseillerDAO conseillerDAO;
	
	public ConseillerServiceImpl() {
		conseillerDAO = new ConseillerDAOImpl();
	}// ctor vide
	
	
	@Override
	public boolean ajouterConseiller(Conseiller pConseiller) {
		return conseillerDAO.add(pConseiller);
	}

	@Override
	public boolean modifierConseiller(Conseiller pConseiller) {
		return conseillerDAO.update(pConseiller);
	}

	@Override
	public boolean supprimerConseiller(Integer pIdConseiller) {
		return conseillerDAO.delete(pIdConseiller);
	}

	@Override
	public List<Conseiller> findAllConseillers() {
		return conseillerDAO.getAll();
	}

	@Override
	public Conseiller findConseillerById(Integer pIdConseiller) {
		return  conseillerDAO.getById(pIdConseiller);
	}

	@Override
	public boolean isConseillerExists(String pId, String pMdp) {
		return conseillerDAO.isConseillerExists(pId, pMdp);
	}


	@Override
	public int findIdConseillerByLogin(String pIdentifiant, String pMotDePasse) {
		return conseillerDAO.getIdConseillerByLogin(pIdentifiant, pMotDePasse);
	}



	@Override
	public List<Conseiller> findAllConseillerByAdmin(int id_admin) {
		return conseillerDAO.getConseillerAllConseiller(id_admin);
	}

}// end classe
